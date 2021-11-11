package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.model.TrackDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveTrack {
    Mono<String> apply(@Valid TrackDTO trackDTO);
}
