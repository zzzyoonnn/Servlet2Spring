package org.servlet2spring.servlet2spring.examples.springSample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("normal")
public class SampleDAOImpl implements SampleDAO {
}
