package co.com.sofka.race.usecases.player;

import co.com.sofka.race.collections.Player;
import co.com.sofka.race.model.PlayerDTO;
import co.com.sofka.race.reposioties.PlayerRepository;
import co.com.sofka.race.util.MapperPlayer;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdatePlayerUseCase implements SavePlayer {

    private final PlayerRepository playerRepository;
    private final MapperPlayer mapperPlayer;

    public UpdatePlayerUseCase(PlayerRepository playerRepository, MapperPlayer mapperPlayer) {
        this.playerRepository = playerRepository;
        this.mapperPlayer = mapperPlayer;
    }

    @Override
    public Mono<String> apply(PlayerDTO playerDTO) {
        return playerRepository
                .save(mapperPlayer.mapperToPlayer(playerDTO.getId()).apply(playerDTO))
                .map(Player::getId);
    }

}
