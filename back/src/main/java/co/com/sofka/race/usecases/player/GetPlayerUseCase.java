package co.com.sofka.race.usecases.player;

import co.com.sofka.race.model.PlayerDTO;
import co.com.sofka.race.reposioties.PlayerRepository;
import co.com.sofka.race.util.MapperPlayer;
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
