package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.usecases.track.GetTrackUseCase;
import co.com.sofka.questions.usecases.track.MoveHorsesUseCase;
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

    public PlayGameUseCase(MoveHorsesUseCase moveHorsesUseCase, GetGameUseCase getGameUseCase, GetTrackUseCase getTrackUseCase, FinishGameUseCase finishGameUseCase) {
        this.moveHorsesUseCase = moveHorsesUseCase;
        this.getGameUseCase = getGameUseCase;
        this.getTrackUseCase = getTrackUseCase;
        this.finishGameUseCase = finishGameUseCase;
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
                        return Mono.just("¡Finish!");
                    }

                    return Mono.just("¡Error!");
                }
        );
    }

}
