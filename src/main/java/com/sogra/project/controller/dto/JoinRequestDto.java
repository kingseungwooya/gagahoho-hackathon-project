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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class JoinRequestDto {

    private String familyName;

    private String name;

    private String role;

    private MultipartFile workoutFile;

    private MultipartFile nutritionFile;

    @Builder
    public JoinRequestDto(String familyName, String name, String role,
                          MultipartFile workoutFile, MultipartFile nutritionFile) {
        this.familyName = familyName;
        this.name = name;
        this.role = role;
        this.workoutFile = workoutFile;
        this.nutritionFile = nutritionFile;
    }

    public Member toEntity(Family family, WorkOutVoice workOutVoice, NutritionVoice nutritionVoice) {
        return Member.builder()
                .family(family)
                .name(name)
                .workOutVoice(workOutVoice)
                .nutritionVoice(nutritionVoice)
                .role(role)
                .build();
    }
}
