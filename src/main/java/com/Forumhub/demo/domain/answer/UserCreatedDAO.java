package com.Forumhub.demo.domain.answer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record UserCreatedDAO(@JsonProperty("mensagem")
                             String message,
                             @JsonProperty("data_criacao")
                             LocalDateTime creationDate,
                             @JsonProperty("topico")
                             String topic,
                             @JsonProperty("autor")
                             String author) {

    public AnswerCreatedDAO(Answer answer) {
        this(answer.getMessage(), answer.getCreationDate(), answer.getTopic().getTitle(), answer.getAuthor().getName());
    }
}
