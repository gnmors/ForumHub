package com.Forumhub.demo.domain.topic;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT new br.com.forumhub.domain.topic.TopicDisplayDAO(t.id, t.title, t.message, t.creationDate, t.status, a.name) FROM Topic t JOIN t.author a")
    Page<TopicDisplayDAO> findAllJoiningNameAuthor(Pageable pageable);
}
