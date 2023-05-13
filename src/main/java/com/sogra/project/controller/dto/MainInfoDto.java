package com.sogra.project.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainInfoDto {

    private final String familyName;

    private final List<MemberInfoDto> memberInfoDtos;

    @Builder
    public MainInfoDto(String familyName, List<MemberInfoDto> memberInfoDtos) {
        this.familyName = familyName;
        this.memberInfoDtos = memberInfoDtos;
    }
}
