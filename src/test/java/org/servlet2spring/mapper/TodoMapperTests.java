package org.servlet2spring.mapper;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.servlet2spring.todo.domain.TodoVO;
import org.servlet2spring.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

  @Autowired(required = false)
  private TodoMapper todoMapper;

  @Test
  public void testGetTime() {
    log.info(todoMapper.getTime());
  }

  // TodoVO 입력 기능 확인
  @Test
  public void testInsert() {
    TodoVO todoVO = TodoVO.builder()
            .title("스프링 입력 테스트")
            .dueDate(LocalDate.now())
            .writer("user00")
            .build();

    todoMapper.insert(todoVO);
  }

  // TodoVO 목록 기능 확인
  @Test
  public void testSelectAll() {
    List<TodoVO> voList = todoMapper.selectAll();
    voList.forEach(vo -> log.info(vo));
  }

  // 조회 기능 확인
  @Test
  public void testSelectOne() {
    TodoVO todoVO = todoMapper.selectOne(1L);

    log.info(todoVO);
  }
}
