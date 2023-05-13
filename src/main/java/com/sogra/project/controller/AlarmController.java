package com.sogra.project.controller;

import com.sogra.project.model.enums.TodoType;
import com.sogra.project.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alarm")
@RequiredArgsConstructor
public class AlarmController {

    private final MemberService memberService;

    @GetMapping("")
    @ApiOperation(value = "오디오파일을 반환하는 api 입니다.")
    public ResponseEntity<?> getAudio(@RequestParam String familyName,
                                      @RequestParam String memberName,
                                      @RequestParam String todoType) {
        if (TodoType.valueOf(todoType) == TodoType.NUTRITION) {
            return ResponseEntity.ok().body(memberService.getNutritionAudio(familyName, memberName));
        }
        return ResponseEntity.ok().body(memberService.getWorkOutAudio(
                familyName, memberName));
    }
}
