package org.zerock.spring.mapper;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.spring.domain.TodoVO;
import org.zerock.spring.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;


@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") //root-context 스프링 컨테이너에 등록된 빈들을 가져다 사용
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper; // Mapper를 이용해서 쿼리 실행

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2023,07,21))
                .writer("user00")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(3L);
        log.info(todoVO);
    }

//    @Test
//    void testSelectList() {
//        //given
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//
//        //when
//        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
//
//        //then
//        voList.forEach(vo -> log.info(vo));
//        Assertions.assertThat(voList).size().isEqualTo(10);
//    }

    @Test
    void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1).size(10).types(new String[]{"t", "w"})
                .keyword("날씨")
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));


    }
}
