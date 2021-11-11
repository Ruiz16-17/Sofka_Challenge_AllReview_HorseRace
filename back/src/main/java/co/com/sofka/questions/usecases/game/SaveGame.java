package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.model.GameDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveGame {
    Mono<String> apply(@Valid GameDTO gameDTO);
}
