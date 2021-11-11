package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.reposioties.GameRepository;
import co.com.sofka.questions.usecases.track.GetTrackUseCase;
import co.com.sofka.questions.util.MapperGame;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class FinishGameUseCase implements SaveGame {

    private final GameRepository gameRepository;
    private final MapperGame mapperGame;
    private final GetTrackUseCase getTrackUseCase;

    public FinishGameUseCase(GameRepository gameRepository, MapperGame mapperGame, GetTrackUseCase getTrackUseCase) {
        this.gameRepository = gameRepository;
        this.mapperGame = mapperGame;
        this.getTrackUseCase = getTrackUseCase;
    }

    @Override
    public Mono<String> apply(GameDTO gameDTO) {

        return getTrackUseCase.apply(gameDTO.getIdTrack()).flatMap(
                trackDTO -> {
                    gameDTO.setInGame(false);
                    gameDTO.setLaneWinner(trackDTO.getLaneWinner());
                    return gameRepository
                            .save(mapperGame.mapperToGame(gameDTO.getId()).apply(gameDTO))
                            .map(Game::getId);
                }
        );
    }

}
