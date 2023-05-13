package com.sogra.project.service;

import com.sogra.project.controller.dto.JoinRequestDto;
import com.sogra.project.model.Family;
import com.sogra.project.model.Member;
import com.sogra.project.model.enums.TodoType;
import com.sogra.project.model.file.NutritionVoice;
import com.sogra.project.model.file.WorkOutVoice;
import com.sogra.project.repository.FamilyRepository;
import com.sogra.project.repository.MemberRepository;
import com.sogra.project.repository.NutritionRepository;
import com.sogra.project.repository.WorkoutRepository;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;
    private final NutritionRepository nutritionRepository;
    private final WorkoutRepository workoutRepository;

    @Override
    public void joinFamily(JoinRequestDto joinRequestDto) throws Exception {
        Family family = familyRepository.findByFamilyId(joinRequestDto.getFamilyName());
        if(memberRepository.existsByFamilyAndName(family, joinRequestDto.getName())) {
            throw new Exception("id duplicated");
        }
        MultipartFile nutFile = joinRequestDto.getNutritionFile();
        String nutFileName = StringUtils.cleanPath(nutFile.getOriginalFilename());
        NutritionVoice nutritionVoice = new NutritionVoice(nutFileName, nutFile.getContentType(), nutFile.getBytes());
        nutritionRepository.save(nutritionVoice);
        MultipartFile workFile = joinRequestDto.getWorkoutFile();
        String workFileName = StringUtils.cleanPath(workFile.getOriginalFilename());
        WorkOutVoice workOutVoice = new WorkOutVoice(workFileName, workFile.getContentType(), workFile.getBytes());
        workoutRepository.save(workOutVoice);
        memberRepository.save(joinRequestDto.toEntity(family, workOutVoice, nutritionVoice));
    }

    @Override
    public WorkOutVoice getWorkOutAudio(String familyName, String memberName) {
        Family family = familyRepository.findByFamilyId(familyName);
        Member member = memberRepository.findByNameAndFamily(memberName, family);
        return member.getWorkOutVoice();
    }

    @Override
    public NutritionVoice getNutritionAudio(String familyName, String memberName) {
        Family family = familyRepository.findByFamilyId(familyName);
        Member member = memberRepository.findByNameAndFamily(memberName, family);
        return member.getNutritionVoice();
    }
}
