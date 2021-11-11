package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.collections.Track;
import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.reposioties.GameRepository;
import co.com.sofka.questions.reposioties.TrackRepository;
import co.com.sofka.questions.util.MapperGame;
import co.com.sofka.questions.util.MapperTrack;
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

        return gameRepository
                .save(mapperGame.mapperToGame(null).apply(gameDTO))
                .map(Game::getId);
    }

}
