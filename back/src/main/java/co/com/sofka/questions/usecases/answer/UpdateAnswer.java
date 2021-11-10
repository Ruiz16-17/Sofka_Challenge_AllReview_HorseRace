package co.com.sofka.questions.usecases.answer;

import co.com.sofka.questions.model.AnswerDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UpdateAnswer {
    Mono<String> apply(@Valid AnswerDTO answerDTO);
}
