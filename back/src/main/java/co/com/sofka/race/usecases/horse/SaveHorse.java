package co.com.sofka.race.usecases.horse;

import co.com.sofka.race.model.HorseDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveHorse {
    Mono<String> apply(@Valid HorseDTO horseDTO);
}
