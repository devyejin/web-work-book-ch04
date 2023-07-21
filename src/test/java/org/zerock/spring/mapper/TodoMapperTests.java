package org.zerock.spring.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.spring.domain.TodoVO;

import java.time.LocalDate;


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
}
