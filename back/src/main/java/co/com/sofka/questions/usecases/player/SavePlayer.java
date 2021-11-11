package co.com.sofka.questions.usecases.player;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.PlayerDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SavePlayer {
    Mono<String> apply(@Valid PlayerDTO playerDTO);
}
