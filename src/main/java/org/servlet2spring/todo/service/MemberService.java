package org.servlet2spring.todo.service;

import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.dto.MemberDTO;
import org.servlet2spring.todo.dto.MemberJoinDTO;
import org.servlet2spring.todo.util.MapperUtil;

public interface MemberService {

  static class MidExistException extends Exception {

  }

  void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}
