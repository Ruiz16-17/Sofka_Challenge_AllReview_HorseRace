package co.com.sofka.questions.util;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.model.GameDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperGame {

    public Function<GameDTO, Game> mapperToGame(String id) {
        return gameDTO -> {
            var game = new Game();
            game.setId(id);
            game.setIdTrack(gameDTO.getIdTrack());
            game.setIdUser(gameDTO.getIdUser());
            game.setInGame(gameDTO.isInGame());
            game.setLaneWinner(gameDTO.getLaneWinner());
            game.setMoneyBet(gameDTO.getMoneyBet());

            return game;
        };
    }

    public Function<Game, GameDTO> mapEntityToGame() {
        return entity -> new GameDTO(
                entity.getId(),
                entity.getIdTrack(),
                entity.getIdUser(),
                entity.isInGame(),
                entity.getLaneWinner(),
                entity.getMoneyBet()
        );
    }

}
