package co.com.sofka.race.usecases.game;

import co.com.sofka.race.collections.Game;
import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.reposioties.GameRepository;
import co.com.sofka.race.util.MapperGame;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateGameUseCase implements SaveGame {

    private final GameRepository gameRepository;
    private final MapperGame mapperGame;

    public CreateGameUseCase(GameRepository gameRepository, MapperGame mapperGame) {
        this.gameRepository = gameRepository;
        this.mapperGame = mapperGame;
    }

    @Override
    public Mono<String> apply(GameDTO gameDTO) {
        gameDTO.setLaneWinner(-1);
        return gameRepository
                .save(mapperGame.mapperToGame(null).apply(gameDTO))
                .map(Game::getId);
    }

}
