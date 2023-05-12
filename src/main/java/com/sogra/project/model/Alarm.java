package com.sogra.project.model;


import static javax.persistence.FetchType.LAZY;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Getter
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long alarmId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo ;

    private Long memberId;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "file_id")
    private FileDB alarmVoice;

    @Builder
    public Alarm(Long alarmId, Todo todo, Long memberId, FileDB alarmVoice) {
        this.alarmId = alarmId;
        this.todo = todo;
        this.memberId = memberId;
        this.alarmVoice = alarmVoice;
    }
}



