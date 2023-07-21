package org.zerock.spring.service;

import org.zerock.spring.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll(); //컨트롤러로 반환하니까 dto

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);

}
