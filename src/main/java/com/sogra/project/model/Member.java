package com.sogra.project.model;


import static javax.persistence.FetchType.LAZY;

import com.sogra.project.model.file.NutritionVoice;
import com.sogra.project.model.file.WorkOutVoice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    private String name;

    private String role;

    private Integer point;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "nut_aud_id")
    private NutritionVoice nutritionVoice;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "work_aud_id")
    private WorkOutVoice workOutVoice;

    @Builder
    public Member(Long memberId, Family family, String name, NutritionVoice nutritionVoice,
                  WorkOutVoice workOutVoice, String role) {
        this.memberId = memberId;
        this.family = family;
        this.name = name;
        this.nutritionVoice = nutritionVoice;
        this.workOutVoice = workOutVoice;
        this.role = role;
        this.point = 0;
    }

    public void updatePoint() {
        point += 10;
    }
}
