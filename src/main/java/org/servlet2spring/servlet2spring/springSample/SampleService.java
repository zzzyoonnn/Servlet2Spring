package org.servlet2spring.servlet2spring.springSample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ToString
@RequiredArgsConstructor
public class SampleService {

  @Autowired
  private final SampleDAO sampleDAO;
}
