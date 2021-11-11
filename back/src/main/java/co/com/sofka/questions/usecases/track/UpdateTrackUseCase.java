package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.collections.Track;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.reposioties.TrackRepository;
import co.com.sofka.questions.util.MapperTrack;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Validated
public class UpdateTrackUseCase implements SaveTrack {
    private final TrackRepository trackRepository;
    private final MapperTrack mapperTrack;

    public UpdateTrackUseCase(MapperTrack mapperTrack, TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
        this.mapperTrack = mapperTrack;
    }

    @Override
    public Mono<String> apply(TrackDTO trackDTO) {
        return trackRepository
                .save(mapperTrack.mapperToTrack(trackDTO.getId()).apply(trackDTO))
                .map(Track::getId);
    }

}
