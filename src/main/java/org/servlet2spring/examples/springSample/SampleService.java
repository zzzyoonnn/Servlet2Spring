package org.servlet2spring.examples.springSample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@ToString
@RequiredArgsConstructor
public class SampleService {

  @Qualifier("normal")
  private final SampleDAO sampleDAO;
}
