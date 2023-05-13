package com.sogra.project.model;

import com.sogra.project.controller.dto.FamilyInfoDto;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@NoArgsConstructor
public class Family {

    @Id
    @Column(name = "family_id")
    private String familyId;

    @Column(name = "family_pw")
    private String password;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "host_role")
    private String hostRole;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @Builder
    public Family(String familyId, String password, String hostName, String hostRole, LocalDate createDate) {
        this.familyId = familyId;
        this.password = password;
        this.hostName = hostName;
        this.hostRole = hostRole;
        this.createDate = createDate;
    }

    public FamilyInfoDto toDto(Long memberCount) {
        return FamilyInfoDto.builder()
                .familyName(familyId)
                .memberCount(memberCount)
                .hostName(hostName)
                .hostRole(hostRole)
                .createdDate(createDate)
                .build();
    }
}
