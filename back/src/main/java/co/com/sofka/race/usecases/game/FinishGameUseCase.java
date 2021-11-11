package co.com.sofka.race.usecases.game;

import co.com.sofka.race.collections.Game;
import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.reposioties.GameRepository;
import co.com.sofka.race.usecases.track.GetTrackUseCase;
import co.com.sofka.race.util.MapperGame;
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
