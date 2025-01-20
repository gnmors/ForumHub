package com.Forumhub.demo.domain.topic;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Table(name = "topicos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "mensagem")
    private String message;
    @Column(name = "data_criacao")
    private LocalDateTime creationDate;
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "autor")
    private User author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public Topic(TopicDataReceiverDTO data) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = data.creationDate();
        this.status = Status.NAO_RESPONDIDO;
    }

    public void updateTopic(TopicUpdatingDataReceiverDTO data) {
        this.title = data.title();
        this.message = data.message();
    }

    public List<AnswerDisplayDAO> getAnswers() {
        return answers.stream().map(AnswerDisplayDAO::new).toList();
    }
}
