package com.sogra.project.repository;


import com.sogra.project.model.Member;
import com.sogra.project.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByTodoId(Long todoId);
    List<Todo> findAllByMember(Member member);
}
