package com.sogra.project.model;


import static javax.persistence.FetchType.LAZY;

import java.io.File;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member ;

    private String todo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Day> cycle;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    @Column(name = "certifying_image")
    private FileDB certifyingImage;

}



