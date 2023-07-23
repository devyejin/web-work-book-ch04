package org.zerock.spring.service;

import org.zerock.spring.dto.PageRequestDTO;
import org.zerock.spring.dto.PageResponseDTO;
import org.zerock.spring.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

//    List<TodoDTO> getAll(); //컨트롤러로 반환하니까 dto

    //그냥 List로 응답할게 아니라 페이지 버튼 등 구성에 필요한거 묶어서
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);

}
