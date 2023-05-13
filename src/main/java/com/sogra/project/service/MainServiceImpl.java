package com.sogra.project.service;

import com.sogra.project.controller.dto.MainInfoDto;
import com.sogra.project.controller.dto.MemberInfoDto;
import com.sogra.project.controller.dto.TodoInfo;
import com.sogra.project.controller.dto.TodoRequestDto;
import com.sogra.project.model.Day;
import com.sogra.project.model.Family;
import com.sogra.project.model.Member;
import com.sogra.project.model.Todo;
import com.sogra.project.repository.FamilyRepository;
import com.sogra.project.repository.MemberRepository;
import com.sogra.project.repository.TodoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class MainServiceImpl implements MainService {

    private final Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    @Override
    public List<String> getFamilyName(String familyName) {
        Family family = familyRepository.findByFamilyId(familyName);
        return memberRepository.findAllByFamily(family)
                .stream()
                .map(entity -> entity.getName())
                .collect(Collectors.toList());
    }

    @Override
    public void addTodo(TodoRequestDto todoRequestDto) {
        logger.info("todo request days first input -> {}", todoRequestDto.getDays().get(0));
        Family family = familyRepository.findByFamilyId(todoRequestDto.getFamilyName());
        Member member = memberRepository.findByNameAndFamily(todoRequestDto.getTargetName(), family);
        todoRepository.save(todoRequestDto.toEntity(member));
    }

    @Override
    public void checkTodo(Long todoId) {
        Todo todo = todoRepository.findByTodoId(todoId);
        todo.getMember().updatePoint();
        todo.update();
    }

    @Override
    public MainInfoDto getMainInfo(String familyName) {

        Day day = Day.builder()
                .dayOfWeek(LocalDate.now().getDayOfWeek())
                .build();

        Family family = familyRepository.findByFamilyId(familyName);
        List<Member> members = memberRepository.findAllByFamily(family);

        return MainInfoDto.builder()
                .familyName(familyName)
                .memberInfoDtos(members.stream()
                        .map(
                                m -> MemberInfoDto.builder()
                                        .memberName(m.getName())
                                        .memberRole(m.getRole())
                                        .point(m.getPoint())
                                        .todoInfos(
                                                todoRepository.findAllByMember(m).stream()
                                                        .filter(todo -> todo.isContainDay(day.getDayOfWeek()))
                                                        .map(todo -> todo.toDto())
                                                        .collect(Collectors.toList())).build())
                        .collect(Collectors.toList()))
                .build();
    }


}
