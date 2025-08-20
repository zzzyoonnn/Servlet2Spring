package org.servlet2spring.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;
//import org.servlet2spring.todo.domain.MemberVO;
//
//public class MemberDAO {
//
//  // 쿠키를 이용해서 해당 사용자 정보 로딩 기능
//  public MemberVO selectUUID(String uuid) throws Exception {
//    String query = "select mid, mpw, mname, uuid from tbl_member where uuid = ?";
//
//    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
//    preparedStatement.setString(1, uuid);
//
//    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//
//    resultSet.next();
//
//    MemberVO memberVO = MemberVO.builder()
//            .mid(resultSet.getString(1))
//            .mpw(resultSet.getString(2))
//            .mname(resultSet.getString(3))
//            .uuid(resultSet.getString(4))
//            .build();
//
//    return memberVO;
//  }
//
//  // 자동 로그인 기능
//  public void updateUuid(String mid, String uuid) throws Exception {
//
//    String sql = "update tbl_member set uuid = ? where mid = ?";
//
//    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//    preparedStatement.setString(1, uuid);
//    preparedStatement.setString(2, mid);
//
//    preparedStatement.executeUpdate();
//  }
//
//  public MemberVO getWithPassword(String mid, String mpw) throws Exception {
//
//    String query = "select mid, mpw, mname from tbl_member where mid = ? and mpw = ?";
//
//    MemberVO memberVO = null;
//
//    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
//    preparedStatement.setString(1, mid);
//    preparedStatement.setString(2, mpw);
//
//    @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
//
//    resultSet.next();
//
//    memberVO = MemberVO.builder()
//            .mid(resultSet.getString(1))
//            .mpw(resultSet.getString(2))
//            .mname(resultSet.getString(3))
//            .build();
//
//    return memberVO;
//  }
//}
