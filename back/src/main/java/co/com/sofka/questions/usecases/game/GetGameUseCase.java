package co.com.sofka.questions.usecases.game;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.reposioties.GameRepository;
import co.com.sofka.questions.util.MapperGame;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetGameUseCase implements Function<String, Mono<GameDTO>> {
    private final GameRepository gameRepository;
    private final MapperGame mapperGame;

    public GetGameUseCase(MapperGame mapperGame, GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.mapperGame = mapperGame;
    }

    @Override
    public Mono<GameDTO> apply(String id) {

        return gameRepository.findById(id)
                .map(mapperGame.mapEntityToGame());
    }

}
