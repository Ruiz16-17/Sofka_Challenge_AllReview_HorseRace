package co.com.sofka.race.usecases.game;

import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.reposioties.GameRepository;
import co.com.sofka.race.util.MapperGame;
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
