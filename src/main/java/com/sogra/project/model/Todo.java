package com.sogra.project.model;


import static javax.persistence.FetchType.LAZY;

import com.sogra.project.controller.dto.TodoInfo;
import com.sogra.project.model.enums.TodoType;
import com.sogra.project.model.file.CertifyingImage;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
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
    private Member member;

    private String alarm;

    private String todo;

    private Integer minute;

    private Integer hour;

    @Enumerated(EnumType.STRING)
    private TodoType todoType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Day> cycle;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "certifying_id")
    private CertifyingImage certifyingImage;

    @Column(name = "open_flag", nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isCheck;

    @Builder
    public Todo(Long todoId, Member member, String alarm, String todo, Integer minute, Integer hour,
                List<Day> cycle, CertifyingImage certifyingImage, TodoType todoType) {
        this.todoId = todoId;
        this.member = member;
        this.alarm = alarm;
        this.todo = todo;
        this.minute = minute;
        this.hour = hour;
        this.cycle = cycle;
        this.certifyingImage = certifyingImage;
        this.isCheck = false;
        this.todoType = todoType;
    }
    public boolean isContainDay(DayOfWeek dayOfWeek) {
        List<?> collect = cycle.stream()
                .map(d -> d.getDayOfWeek().toString())
                .collect(Collectors.toList());

        return collect.contains(dayOfWeek.toString());
    }

    public void update() {
        if (isCheck) {
            this.isCheck = true;
        } else {
            this.isCheck = false;
        }
    }

    public TodoInfo toDto() {
        return TodoInfo.builder()
                .todo(todo)
                .todoType(todoType)
                .todoId(todoId)
                .time(LocalDateTime.of(2000, 1, 1, hour, minute))
                .build();
    }

}



