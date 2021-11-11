package co.com.sofka.race.usecases.game;

import co.com.sofka.race.collections.Game;
import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.reposioties.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class CreateGameUseCaseTest {

    @SpyBean
    CreateGameUseCase createGameUseCase;

    @MockBean
    GameRepository gameRepository;

    @Test
    void createGameUseCaseTest(){
        var gameDTO = new GameDTO("1","1","1",true,-1,2L);
        var game = new Game();

        game.setId(gameDTO.getId());
        game.setIdTrack(gameDTO.getIdTrack());
        game.setIdUser(gameDTO.getIdUser());
        game.setInGame(gameDTO.isInGame());
        game.setLaneWinner(gameDTO.getLaneWinner());
        game.setMoneyBet(gameDTO.getMoneyBet());

        Mockito.when(gameRepository.save(Mockito.any(Game.class))).thenReturn(Mono.just(game));

        var resultDTO = createGameUseCase.apply(gameDTO);
        var resultGameDTO = resultDTO.block();

        assert resultGameDTO != null;

        Assertions.assertEquals(resultGameDTO, gameDTO.getId());

    }

}