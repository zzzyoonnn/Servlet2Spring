package org.servlet2spring.servlet2spring.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;
import org.servlet2spring.servlet2spring.todo.domain.MemberVO;

public class MemberDAO {

  public MemberVO getWithPassword(String mid, String mpw) throws Exception {

    String query = "select mid, mpw, mname from tbl_member where mid = ? and mpw = ?";

    MemberVO memberVO = null;

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, mid);
    preparedStatement.setString(2, mpw);

    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();

    memberVO = MemberVO.builder()
            .mid(resultSet.getString(1))
            .mpw(resultSet.getString(2))
            .mname(resultSet.getString(3))
            .build();

    return memberVO;
  }
}
