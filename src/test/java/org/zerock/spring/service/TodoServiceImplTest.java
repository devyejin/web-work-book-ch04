package org.zerock.spring.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.spring.dto.PageRequestDTO;
import org.zerock.spring.dto.PageResponseDTO;
import org.zerock.spring.dto.TodoDTO;

import java.time.LocalDate;


@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml") //servlet-context로 해서 에러남 (눈 빠지는줄)
class TodoServiceImplTest {

    @Autowired
    private TodoService todoService;

    @org.junit.Test
    void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("todo 테스트용 타이블")
                .writer("todo 작성자")
                .dueDate(LocalDate.now())
                .build();


        todoService.register(todoDTO);
    }

    @Test
    void testPaging() {
        //given
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        //when
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        //then
        log.info("responseDTO={}", responseDTO);
        responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));

    }
}