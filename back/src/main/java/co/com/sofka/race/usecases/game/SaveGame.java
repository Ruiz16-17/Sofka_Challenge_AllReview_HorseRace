package co.com.sofka.race.usecases.game;

import co.com.sofka.race.model.GameDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveGame {
    Mono<String> apply(@Valid GameDTO gameDTO);
}
