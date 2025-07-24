package org.servlet2spring.todo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.servlet2spring.todo.domain.Reply;
import org.servlet2spring.todo.dto.ReplyDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.addMappings(new PropertyMap<Reply, ReplyDTO>() {
      @Override
      protected void configure() {
        // Reply 객체 자체의 regdate를 ReplyDTO의 regDate에 매핑
        map().setRegDate(source.getRegdate());
        // Reply 객체 자체의 moddate를 ReplyDTO의 modDate에 매핑
        map().setModDate(source.getModdate());
      }
    });

    return modelMapper;
  }
}
