package co.com.sofka.questions.usecases.player;

import co.com.sofka.questions.model.PlayerDTO;
import co.com.sofka.questions.reposioties.PlayerRepository;
import co.com.sofka.questions.util.MapperPlayer;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetPlayerUseCase implements Function<String, Mono<PlayerDTO>> {

    private final PlayerRepository playerRepository;
    private final MapperPlayer mapperPlayer;

    public GetPlayerUseCase(PlayerRepository playerRepository, MapperPlayer mapperPlayer) {
        this.playerRepository = playerRepository;
        this.mapperPlayer = mapperPlayer;
    }

    @Override
    public Mono<PlayerDTO> apply(String id) {

        return playerRepository.findById(id)
                .map(mapperPlayer.mapEntityToPlayer());
    }

}
