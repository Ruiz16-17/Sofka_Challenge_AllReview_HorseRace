package co.com.sofka.questions.util;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.collections.Player;
import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.PlayerDTO;
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
