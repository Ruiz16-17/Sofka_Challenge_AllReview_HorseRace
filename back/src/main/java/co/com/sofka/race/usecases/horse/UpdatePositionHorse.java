package co.com.sofka.race.usecases.horse;

import co.com.sofka.race.model.HorseDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface UpdatePositionHorse {
    Mono<HorseDTO> apply(@Valid HorseDTO horseDTO);
}
