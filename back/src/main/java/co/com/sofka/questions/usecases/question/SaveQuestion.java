package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.model.QuestionDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveQuestion {
    Mono<String> apply(@Valid QuestionDTO questionDTO);
}
