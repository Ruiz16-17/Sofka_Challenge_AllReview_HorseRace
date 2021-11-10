package co.com.sofka.questions.usecases.horse;

import co.com.sofka.questions.model.HorseDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UpdatePositionHorse {
    Mono<HorseDTO> apply(@Valid HorseDTO horseDTO);
}
