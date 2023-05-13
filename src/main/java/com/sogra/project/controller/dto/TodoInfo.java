package com.sogra.project.controller.dto;

import com.sogra.project.model.enums.TodoType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoInfo {
    private final TodoType todoType;
    private final String todo;
    private final Long todoId;
    private final LocalDateTime time;

    @Builder
    public TodoInfo(TodoType todoType, String todo, Long todoId, LocalDateTime time) {
        this.todoType = todoType;
        this.todo = todo;
        this.todoId = todoId;
        this.time = time;
    }
}
