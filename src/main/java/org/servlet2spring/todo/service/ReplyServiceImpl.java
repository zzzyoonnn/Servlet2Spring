package org.servlet2spring.todo.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.Reply;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.servlet2spring.todo.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyRepository replyRepository;
  private final ModelMapper modelMapper;

  @Override
  public Long register(ReplyDTO replyDTO) {
    Reply reply = modelMapper.map(replyDTO, Reply.class);
    Long rno = replyRepository.save(reply).getRno();

    return rno;
  }

  @Override
  public ReplyDTO read(Long rno) {
    Optional<Reply> replyOptional = replyRepository.findById(rno);
    Reply reply = replyOptional.orElseThrow();

    return modelMapper.map(reply, ReplyDTO.class);
  }

  @Override
  public void modify(ReplyDTO replyDTO) {
    Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
    Reply reply = replyOptional.orElseThrow();
    reply.changeText(replyDTO.getReplyText());
    replyRepository.save(reply);
  }

  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }
}
