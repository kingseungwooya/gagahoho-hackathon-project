package com.sogra.project.controller.dto;

import com.sogra.project.model.Family;
import com.sogra.project.model.Member;
import com.sogra.project.model.file.NutritionVoice;
import com.sogra.project.model.file.WorkOutVoice;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class FamilyDto {

    private String familyName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private String password;

    private String hostName;

    private String hostRole;

    private MultipartFile workoutFile;

    private MultipartFile nutritionFile;

    @Builder
    public FamilyDto(String familyName, String password, String hostName, String hostRole,
                     MultipartFile workoutFile, MultipartFile nutritionFile) {
        this.familyName = familyName;
        this.password = password;
        this.createdDate = LocalDate.now();
        this.hostName = hostName;
        this.hostRole = hostRole;
        this.workoutFile = workoutFile;
        this.nutritionFile = nutritionFile;
    }

    public Family toFamily(String encryptPassword) {
        return Family.builder()
                .familyId(familyName)
                .hostName(hostName)
                .hostRole(hostRole)
                .password(encryptPassword)
                .createDate(createdDate)
                .build();
    }

    public Member toMember(Family family, NutritionVoice nutritionVoice, WorkOutVoice workOutVoice) {
        return Member.builder()
                .name(hostName)
                .nutritionVoice(nutritionVoice)
                .workOutVoice(workOutVoice)
                .family(family)
                .role(hostRole)
                .build();
    }
}
