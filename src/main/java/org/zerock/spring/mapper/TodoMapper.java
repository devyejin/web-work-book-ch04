package org.zerock.spring.mapper;

import org.zerock.spring.domain.TodoVO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);
}
