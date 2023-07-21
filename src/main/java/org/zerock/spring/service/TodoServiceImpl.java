package org.zerock.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.spring.domain.TodoVO;
import org.zerock.spring.dto.TodoDTO;
import org.zerock.spring.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final ModelMapper modelMapper;

    private final TodoMapper todoMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);

        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO = todoMapper.selectOne(tno);
         return modelMapper.map(todoVO, TodoDTO.class);

    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        //DTO->VO
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }


}
