package org.zerock.spring.mapper;

import org.zerock.spring.domain.TodoVO;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);
}
