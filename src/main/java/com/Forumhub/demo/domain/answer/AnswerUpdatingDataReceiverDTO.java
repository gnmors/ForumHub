package com.Forumhub.demo.domain.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerUpdatingDataReceiverDTO(@NotNull
                                            Long id,
                                            @JsonProperty("mensagem")
                                            @NotBlank
                                            String message) {
}
