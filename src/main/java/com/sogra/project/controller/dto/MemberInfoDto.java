package com.sogra.project.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoDto {

    private final String memberName;

    private final String memberRole;

    private final Integer point;

    private final List<TodoInfo> todoInfos;

    @Builder
    public MemberInfoDto(String memberName, String memberRole, Integer point,
                         List<TodoInfo> todoInfos) {
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.point = point;
        this.todoInfos = todoInfos;
    }
}
