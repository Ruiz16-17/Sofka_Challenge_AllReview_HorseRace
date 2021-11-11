package co.com.sofka.race.usecases.track;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class RestartTrackUseCase {

    private final UpdateTrackUseCase updateTrackUseCase;
    private final GetTrackUseCase getTrackUseCase;

    public RestartTrackUseCase(UpdateTrackUseCase updateTrackUseCase, GetTrackUseCase getTrackUseCase) {
        this.updateTrackUseCase = updateTrackUseCase;
        this.getTrackUseCase = getTrackUseCase;
    }

    public Mono<String> apply(String trackId) {

        return getTrackUseCase.apply(trackId).flatMap(
                foundTRackDTO ->{
                    foundTRackDTO.setRacecourse(new int[foundTRackDTO.getLanes().length][foundTRackDTO.getKm()*10]);
                    foundTRackDTO.setCompleted(false);
                    foundTRackDTO.setLaneWinner(-1);
                    return updateTrackUseCase.apply(foundTRackDTO);
                }
        ).map(message -> "¡Finish!");
    }

}
