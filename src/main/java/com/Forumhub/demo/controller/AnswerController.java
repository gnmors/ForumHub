package com.Forumhub.demo.controller;

import com.Forumhub.demo.domain.answer.AnswerRepository;
import com.Forumhub.demo.domain.answer.AnswerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class AnswerController {

        @Autowired
        private AnswerService answerService;

        @Autowired
        private AnswerRepository answerRepository;

        @PostMapping
        @Transactional
        public ResponseEntity createAnswer(@RequestBody @Valid AnswerDataReceiverDTO data, UriComponentsBuilder uriBuilder) {
            var answer = answerService.createAnswer(data);
            answerRepository.save(answer);
            var location = uriBuilder.path("/respostas/{id}").buildAndExpand(answer.getId()).toUri();
            return ResponseEntity.created(location).body(new AnswerCreatedDAO(answer));
        }

        @GetMapping("/solution/{id}")
        @Transactional
        public ResponseEntity setAsSolution(@PathVariable Long id) {
            var answer = answerRepository.getReferenceById(id);
            answerService.setAsSolution(answer);
            return ResponseEntity.ok().build();
        }

        @GetMapping
        public ResponseEntity<Page<AnswerDisplayDAO>> displayAnswers(@PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.ASC) Pageable pageable) {
            var answers = answerRepository.findAll(pageable).map(AnswerDisplayDAO::new);
            return ResponseEntity.ok(answers);
        }

        @GetMapping("/topico/{id}")
        public ResponseEntity<Page<AnswerDisplayDetailDAO>> displayAllAnswersByTopicId(@PathVariable Long id, @PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.ASC) Pageable pageable) {
            var answers = answerRepository.findAllByTopicId(id, pageable).map(AnswerDisplayDetailDAO::new);
            if (answers.getContent().isEmpty()) {
                throw new EntityNotFoundException();
            }
            return ResponseEntity.ok(answers);
        }

        @GetMapping("/{idAnswer}")
        public ResponseEntity detailAnswer(@PathVariable Long idAnswer) {
            var answer = answerRepository.getReferenceById(idAnswer);
            return ResponseEntity.ok(new AnswerDisplayDetailDAO(answer));
        }

        @PutMapping
        @Transactional
        public ResponseEntity updateAnswer(@RequestBody @Valid AnswerUpdatingDataReceiverDTO data) {
            var answer = answerRepository.getReferenceById(data.id());
            answerService.validateAuthorAuthorizationUpdate(answer);
            answer.updateAnswer(data);
            return ResponseEntity.ok(new AnswerDisplayDetailDAO(answer));
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity deleteAnswer(@PathVariable Long id) {
            var answer = answerRepository.getReferenceById(id);
            answerService.validateAuthorAuthorizationDelete(answer);
            answerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
}
