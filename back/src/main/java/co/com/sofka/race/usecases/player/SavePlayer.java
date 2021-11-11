package co.com.sofka.race.usecases.player;

import co.com.sofka.race.model.PlayerDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SavePlayer {
    Mono<String> apply(@Valid PlayerDTO playerDTO);
}
