package com.sogra.project.controller;

import com.sogra.project.controller.dto.MainInfoDto;
import com.sogra.project.controller.dto.TodoRequestDto;
import com.sogra.project.service.MainService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/plan")
    @ApiOperation(value = "계획을 추가할 수 있다.")
    public ResponseEntity<?> addTodo(@RequestBody TodoRequestDto todoRequestDto) {
        mainService.addTodo(todoRequestDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/plan")
    @ApiOperation(value = "내 가족들 이름을 가져올 수 있다.")
    public ResponseEntity<List<String>> getFamilyName(@RequestParam String familyName) {
        return ResponseEntity.ok().body(mainService.getFamilyName(familyName));
    }

    @PostMapping("/plan/check")
    @ApiOperation(value = "계획을 check해서 미성공 <-> 성공 양방향 가능")
    public ResponseEntity<?> checkTodo(@RequestParam Long todoId) {
        mainService.checkTodo(todoId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/")
    @ApiOperation(value = "main 화면")
    public ResponseEntity<MainInfoDto> getMainForm(@RequestParam String familyName) {
        return ResponseEntity.ok().body(mainService.getMainInfo(familyName));
    }
}
