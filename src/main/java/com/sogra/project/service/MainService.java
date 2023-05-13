package com.sogra.project.service;

import com.sogra.project.controller.dto.MainInfoDto;
import com.sogra.project.controller.dto.TodoRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MainService {
    List<String> getFamilyName(String familyName);

    void addTodo(TodoRequestDto todoRequestDto);

    void checkTodo(Long memberId);

    MainInfoDto getMainInfo(String familyName);
}
