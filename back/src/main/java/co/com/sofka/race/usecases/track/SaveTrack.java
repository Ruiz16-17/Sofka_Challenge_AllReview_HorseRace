package co.com.sofka.race.usecases.track;

import co.com.sofka.race.model.TrackDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveTrack {
    Mono<String> apply(@Valid TrackDTO trackDTO);
}
