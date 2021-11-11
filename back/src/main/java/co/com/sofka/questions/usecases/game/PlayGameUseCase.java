package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.usecases.track.GetTrackUseCase;
import co.com.sofka.questions.usecases.track.MoveHorsesUseCase;
import co.com.sofka.questions.usecases.track.RestartTrackUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class PlayGameUseCase {

    private final MoveHorsesUseCase moveHorsesUseCase;
    private final GetGameUseCase getGameUseCase;
    private final GetTrackUseCase getTrackUseCase;
    private final FinishGameUseCase finishGameUseCase;
    private final RestartTrackUseCase restartTrackUseCase;

    public PlayGameUseCase(MoveHorsesUseCase moveHorsesUseCase, GetGameUseCase getGameUseCase, GetTrackUseCase getTrackUseCase, FinishGameUseCase finishGameUseCase, RestartTrackUseCase restartTrackUseCase) {
        this.moveHorsesUseCase = moveHorsesUseCase;
        this.getGameUseCase = getGameUseCase;
        this.getTrackUseCase = getTrackUseCase;
        this.finishGameUseCase = finishGameUseCase;
        this.restartTrackUseCase = restartTrackUseCase;
    }


    private Mono<String> verifyGameState(GameDTO gameDTO) {
        return getTrackUseCase.apply(gameDTO.getIdTrack())
                .flatMap(
                        trackDTO -> {
                            if (trackDTO.isCompleted()) {
                                return finishGameUseCase.apply(gameDTO);
                            }
                            return moveHorsesUseCase.apply(gameDTO.getIdTrack());
                        }
                );
    }

    public Mono<String> playGame(GameDTO gameDTO) {

        return getGameUseCase.apply(gameDTO.getId()).flatMap(
                foundGameDTO -> {

                    if (foundGameDTO.isInGame()) {

                        return verifyGameState(foundGameDTO);

                    }
                    if (!foundGameDTO.isInGame()) {
                        return restartTrackUseCase.apply(foundGameDTO.getIdTrack());
                    }

                    return Mono.just("¡Error!");
                }
        );
    }

}
