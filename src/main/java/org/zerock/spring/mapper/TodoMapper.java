package org.zerock.spring.mapper;

import org.zerock.spring.domain.TodoVO;
import org.zerock.spring.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    int getCount(PageRequestDTO pageRequestDTO); //전체 데이터 수


}
