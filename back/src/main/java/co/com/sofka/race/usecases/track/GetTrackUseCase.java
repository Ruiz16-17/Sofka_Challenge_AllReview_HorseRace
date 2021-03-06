package co.com.sofka.race.usecases.track;

import co.com.sofka.race.model.TrackDTO;
import co.com.sofka.race.reposioties.TrackRepository;
import co.com.sofka.race.util.MapperTrack;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetTrackUseCase implements Function<String, Mono<TrackDTO>> {
    private final TrackRepository trackRepository;
    private final MapperTrack mapperTrack;

    public GetTrackUseCase(MapperTrack mapperTrack, TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
        this.mapperTrack = mapperTrack;
    }

    @Override
    public Mono<TrackDTO> apply(String id) {

        return trackRepository.findById(id)
                .map(mapperTrack.mapEntityToTrack());
    }

}
