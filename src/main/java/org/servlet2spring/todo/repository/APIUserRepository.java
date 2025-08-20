package org.servlet2spring.todo.repository;

import org.servlet2spring.todo.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
