package com.sogra.project.model;


import static javax.persistence.FetchType.LAZY;

import java.io.File;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    private String name;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    private FileDB profileImage;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    private FileDB voice;



}
