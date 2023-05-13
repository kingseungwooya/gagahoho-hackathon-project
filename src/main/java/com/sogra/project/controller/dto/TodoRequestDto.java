package com.sogra.project.controller.dto;

import com.sogra.project.model.Day;
import com.sogra.project.model.Member;
import com.sogra.project.model.Todo;
import com.sogra.project.model.enums.TodoType;
import io.swagger.annotations.ApiOperation;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    private String todo;
    private String todoType;
    private String familyName;
    private String targetName;
    private String voice;
    private List<String> days;
    private int minute;
    private int hour;

    public Todo toEntity(Member targetMember) {
        return Todo.builder()
                .todo(todo)
                .alarm(voice)
                .member(targetMember)
                .cycle(days.stream().map( s -> Day.builder()
                        .dayOfWeek(DayOfWeek.valueOf(s))
                        .build())
                        .collect(Collectors.toList()))
                .minute(minute)
                .hour(hour)
                .todoType(TodoType.valueOf(todoType))
                .build();
    }
}
