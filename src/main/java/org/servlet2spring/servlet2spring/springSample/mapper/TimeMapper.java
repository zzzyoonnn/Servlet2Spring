package org.servlet2spring.servlet2spring.springSample.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

  @Select("select now()")
  String getTime();
}
