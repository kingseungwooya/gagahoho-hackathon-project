package com.sogra.project.controller.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FamilyInfoDto {

    private final String familyName;

    private final LocalDate createdDate;

    private final Long memberCount;

    private final String hostName;

    private final String hostRole;

    @Builder
    public FamilyInfoDto(String familyName, LocalDate createdDate, Long memberCount, String hostName,
                         String hostRole) {
        this.familyName = familyName;
        this.createdDate = createdDate;
        this.memberCount = memberCount;
        this.hostName = hostName;
        this.hostRole = hostRole;
    }
}
