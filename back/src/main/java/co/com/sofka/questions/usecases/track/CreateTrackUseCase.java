package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.collections.Track;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.reposioties.TrackRepository;
import co.com.sofka.questions.util.MapperTrack;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateTrackUseCase implements SaveTrack {
    private final TrackRepository trackRepository;
    private final MapperTrack mapperTrack;

    public CreateTrackUseCase(MapperTrack mapperTrack, TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
        this.mapperTrack = mapperTrack;
    }

    @Override
    public Mono<String> apply(TrackDTO trackDTO) {

        int[][] racecourse = new int[trackDTO.getLanes().length][trackDTO.getKm()*10];
        trackDTO.setRacecourse(racecourse);
        trackDTO.setCompleted(false);
        return trackRepository
                .save(mapperTrack.mapperToTrack(null).apply(trackDTO))
                .map(Track::getId);
    }

}
