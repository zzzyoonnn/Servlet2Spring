package org.servlet2spring.servlet2spring.todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import org.servlet2spring.servlet2spring.todo.domain.TodoVO;

public class TodoDAO {

  public TodoVO selectOne(Long no) throws Exception {
    String sql = "select * from tbl_todo where no = ?";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setLong(1, no);

    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();
    TodoVO vo = TodoVO.builder()
            .no(resultSet.getLong("no"))
            .title(resultSet.getString("title"))
            .dueDate(resultSet.getDate("dueDate").toLocalDate())
            .finished(resultSet.getBoolean("finished"))
            .build();

    return vo;
  }

  public List<TodoVO> selectAll() throws Exception {
    String sql = "select * from tbl_todo";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    List<TodoVO> list = new ArrayList<>();

    while (resultSet.next()) {
      TodoVO vo = TodoVO.builder()
              .no(resultSet.getLong("no"))
              .title(resultSet.getString("title"))
              .dueDate(resultSet.getDate("dueDate").toLocalDate())
              .finished(resultSet.getBoolean("finished"))
              .build();

      list.add(vo);
    }

    return list;
  }

  public void insert(TodoVO vo) throws Exception {
    String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setString(1, vo.getTitle());
    preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
    preparedStatement.setBoolean(3, vo.isFinished());

    preparedStatement.executeUpdate();
  }

  public String getTime2() throws Exception {

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();

    String now = resultSet.getString(1);

    return now;
  }

  public String getTime() {

    String now = null;

    try(Connection connection = ConnectionUtil.INSTANCE.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("select now()");
    ResultSet resultSet = preparedStatement.executeQuery();) {

      resultSet.next();

      now = resultSet.getString(1);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return now;
  }
}
