package com.Forumhub.demo.domain.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Page<Answer> findAllByTopicId(Long id, Pageable pageable);
}
