package org.servlet2spring.todo.service;

import org.servlet2spring.todo.dto.MemberJoinDTO;

public interface MemberService {

  static class MidExistException extends Exception {

  }

  void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}
