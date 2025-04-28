package org.servlet2spring.servlet2spring.springSample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@ToString
public class SampleDAO {

  @Autowired
  private SampleDAO sampleDAO;
}
