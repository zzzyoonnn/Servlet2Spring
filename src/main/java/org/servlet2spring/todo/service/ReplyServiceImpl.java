package org.servlet2spring.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.servlet2spring.todo.domain.Reply;
import org.servlet2spring.todo.dto.PageRequestDTO;
import org.servlet2spring.todo.dto.PageResponseDTO;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.servlet2spring.todo.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  @Override
  public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
    Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0? 0:
            pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("rno").ascending());

    Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

    List<ReplyDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(
            Collectors.toList());

    return PageResponseDTO.<ReplyDTO>withAll()
            .pageRequestDTO(pageRequestDTO)
            .dtoList(dtoList)
            .total((int)result.getTotalElements())
            .build();
  }
}
