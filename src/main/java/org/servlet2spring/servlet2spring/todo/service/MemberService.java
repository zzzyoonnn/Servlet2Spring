package org.servlet2spring.servlet2spring.todo.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.servlet2spring.todo.dao.MemberDAO;
import org.servlet2spring.servlet2spring.todo.domain.MemberVO;
import org.servlet2spring.servlet2spring.todo.dto.MemberDTO;
import org.servlet2spring.servlet2spring.todo.util.MapperUtil;

@Log4j2
public enum MemberService {
  INSTANCE;

  private MemberDAO memberDAO;
  private ModelMapper modelMapper;

  MemberService() {
    memberDAO = new MemberDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }

  public MemberDTO login(String mid, String mpw) throws Exception {

    MemberVO memberVO = memberDAO.getWithPassword(mid, mpw);

    MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);

    return memberDTO;
  }
}
