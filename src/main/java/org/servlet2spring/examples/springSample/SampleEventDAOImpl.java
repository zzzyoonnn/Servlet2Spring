package org.servlet2spring.examples.springSample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("event")
public class SampleEventDAOImpl implements SampleDAO{
}
