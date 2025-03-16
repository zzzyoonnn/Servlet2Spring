package org.servlet2spring.servlet2spring.todo.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
  INSTANCE;

  private ModelMapper modelMapper;

  MapperUtil() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
            .setMatchingStrategy(MatchingStrategies.STRICT);
  }

  public ModelMapper getModelMapper() {
    return modelMapper;
  }
}
