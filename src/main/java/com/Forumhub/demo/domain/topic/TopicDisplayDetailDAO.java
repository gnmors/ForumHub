package com.Forumhub.demo.domain.topic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record TopicDisplayDetailDAO(@JsonProperty("titulo")
                                    String title,
                                    @JsonProperty("mensagem")
                                    String message,
                                    @JsonProperty("data_criacao")
                                    LocalDateTime creationDate,
                                    Status status,
                                    @JsonProperty("autor")
                                    String author,
                                    List<AnswerDisplayDAO> answers) {

    public TopicDisplayDetailDAO(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getAuthor().getName(), topic.getAnswers());
    }
}