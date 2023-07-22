package org.zerock.spring.service;

import org.zerock.spring.domain.TodoVO;
import org.zerock.spring.dto.PageRequestDTO;
import org.zerock.spring.dto.PageResponseDTO;
import org.zerock.spring.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

   // List<TodoDTO> getAll(); //컨트롤러로 반환하니까 dto

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);



}
