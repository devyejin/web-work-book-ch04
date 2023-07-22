package org.zerock.spring.mapper;

import org.zerock.spring.domain.TodoVO;
import org.zerock.spring.dto.PageRequestDTO;
import org.zerock.spring.dto.TodoDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO); //현재 페이지(page), 가져올 데이터 사이즈(size) 넘겨주고 받기

    int getCount(PageRequestDTO pageRequestDTO); //검색고려해서 dto넣음
}
