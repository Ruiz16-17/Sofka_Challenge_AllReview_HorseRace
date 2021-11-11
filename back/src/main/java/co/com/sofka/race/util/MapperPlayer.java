package co.com.sofka.race.util;

import co.com.sofka.race.collections.Player;
import co.com.sofka.race.model.PlayerDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperPlayer {

    public Function<PlayerDTO, Player> mapperToPlayer(String id) {
        return playerDTO -> {
            var player = new Player();
            player.setId(id);
            player.setEmail(playerDTO.getEmail());
            player.setName(playerDTO.getName());
            player.setBetCarriel(playerDTO.getBetCarriel());
            player.setMoney(playerDTO.getMoney());

            return player;
        };
    }

    public Function<Player, PlayerDTO> mapEntityToPlayer() {
        return entity -> new PlayerDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getBetCarriel(),
                entity.getMoney()
        );
    }


}
