package com.sogra.project.controller;


import com.sogra.project.controller.dto.FamilyDto;
import com.sogra.project.controller.dto.JoinRequestDto;
import com.sogra.project.controller.dto.FamilyInfoDto;
import com.sogra.project.service.FamilyService;
import com.sogra.project.service.MainServiceImpl;
import com.sogra.project.service.MemberService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/family")
@RequiredArgsConstructor
public class FamilyController {

    private final Logger logger = LoggerFactory.getLogger(FamilyController.class);

    private final MemberService memberService;
    private final FamilyService familyService;

    @PostMapping("/duplication-check")
    @ApiOperation(notes = "가족 생성시 이름 중복 체크"
            , value = "return : boolean")
    public ResponseEntity<Boolean> familyNameDuplicationCheck(String familyName) {
        return new ResponseEntity<>(familyService.isDuplicated(familyName), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(notes = "랜딩페이지 family 생성."
            , value = "return : none(void) 에러시 ")
    public ResponseEntity<?> create(@RequestParam String familyName,
                                    @RequestParam String hostName,
                                    @RequestParam String hostRole,
                                    @RequestParam String password,
                                    @RequestParam(name = "nutrition_file", required = false) MultipartFile nutritionFile,
                                    @RequestParam(name = "work_file", required = false) MultipartFile workoutFile) {
        try {
            logger.info("input familyName is -> {}", familyName);
            familyService.createFamily(
                    FamilyDto.builder().familyName(familyName)
                            .password(password)
                            .hostName(hostName)
                            .hostRole(hostRole)
                            .nutritionFile(nutritionFile)
                            .workoutFile(workoutFile)
                            .build());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    @ApiOperation(notes = "랜딩페이지 family 검색."
            , value = "return : familyId, createdDate, memberCount, creater, createrRole")
    public ResponseEntity<List<FamilyInfoDto>> search(@RequestParam String familyName) {
        return ResponseEntity.ok().body(familyService.search(familyName));
    }

    @PostMapping("/password-check")
    @ApiOperation(notes = "family 찾은 후 비밀번호 입력"
            , value = "return boolean")
    public ResponseEntity<Boolean> familyEntry(@RequestParam String familyName, @RequestParam String password) {
        return new ResponseEntity<>(familyService.isEnter(familyName, password), HttpStatus.OK);
    }

    @PostMapping("/join")
    @ApiOperation(notes = "가족 방 참가"
            , value = "중복 이름은 안됌")
    public ResponseEntity<?> join(@RequestParam String familyName,
                                  @RequestParam String name,
                                  @RequestParam String role,
                                  @RequestParam(name = "nutrition_file", required = false) MultipartFile nutritionFile,
                                  @RequestParam(name = "work_file", required = false) MultipartFile workoutFile) {
        try {
            memberService.joinFamily(
                    JoinRequestDto.builder()
                            .familyName(familyName)
                            .name(name)
                            .role(role)
                            .nutritionFile(nutritionFile)
                            .workoutFile(workoutFile)
                            .build());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
