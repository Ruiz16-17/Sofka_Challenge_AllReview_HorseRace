package co.com.sofka.questions.usecases.horse;

import co.com.sofka.questions.model.HorseDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveHorse {
    Mono<String> apply(@Valid HorseDTO horseDTO);
}
