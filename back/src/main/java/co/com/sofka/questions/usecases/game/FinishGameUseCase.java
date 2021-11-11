package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.reposioties.GameRepository;
import co.com.sofka.questions.util.MapperGame;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class FinishGameUseCase implements SaveGame {

    private final GameRepository gameRepository;
    private final MapperGame mapperGame;

    public FinishGameUseCase(GameRepository gameRepository, MapperGame mapperGame) {
        this.gameRepository = gameRepository;
        this.mapperGame = mapperGame;
    }

    @Override
    public Mono<String> apply(GameDTO gameDTO) {
        gameDTO.setInGame(false);
        return gameRepository
                .save(mapperGame.mapperToGame(gameDTO.getId()).apply(gameDTO))
                .map(Game::getId);
    }

}
