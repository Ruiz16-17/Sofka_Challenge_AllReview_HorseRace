package co.com.sofka.race.usecases.game;

import co.com.sofka.race.collections.Game;
import co.com.sofka.race.collections.Player;
import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.model.PlayerDTO;
import co.com.sofka.race.reposioties.GameRepository;
import co.com.sofka.race.reposioties.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class EarnedMoneyBetPlayerGameUseCaseTest {

    @SpyBean
    EarnedMoneyBetPlayerGameUseCase earnedMoneyBetPlayerGameUseCase;

    @MockBean
    PlayerRepository playerRepository;

    @MockBean
    GameRepository gameRepository;

    @Test
    void earnedMoneyBetPlayerGameUseCaseTest(){
        var gameDTO = new GameDTO("1","1","1",true,-1,1L);
        var game = new Game();

        game.setId(gameDTO.getId());
        game.setIdTrack(gameDTO.getIdTrack());
        game.setIdUser(gameDTO.getIdUser());
        game.setInGame(gameDTO.isInGame());
        game.setLaneWinner(gameDTO.getLaneWinner());
        game.setMoneyBet(gameDTO.getMoneyBet());

        var playerDTO = new PlayerDTO("1","player@Email","namePlayer",2,2L);
        var player = new Player();

        player.setId(playerDTO.getId());
        player.setEmail(playerDTO.getEmail());
        player.setName(playerDTO.getName());
        player.setBetCarriel(playerDTO.getBetCarriel());
        player.setMoney(playerDTO.getMoney());
        playerDTO.setMoneyBet(1L);

        Mockito.when(gameRepository.save(Mockito.any(Game.class))).thenReturn(Mono.just(game));
        Mockito.when(playerRepository.save(Mockito.any(Player.class))).thenReturn(Mono.just(player));
        Mockito.when(gameRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(game));
        Mockito.when(playerRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(player));

        var resultMono = earnedMoneyBetPlayerGameUseCase.apply(gameDTO);
        var result = resultMono.block();

        Long moneyAfterEarnBet = player.getMoney() + playerDTO.getMoneyBet();

        assert result != null;

        Assertions.assertEquals(Long.parseLong(result), moneyAfterEarnBet);

    }


}