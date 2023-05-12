package com.sogra.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Family {

    @Id
    @Column(name = "family_id")
    private String familyId;

    @Column(name = "family_pw")
    private String password;

    @Builder
    public Family(String familyId, String password) {
        this.familyId = familyId;
        this.password = password;
    }
}
