package org.servlet2spring.todo.repository;

import org.servlet2spring.todo.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
