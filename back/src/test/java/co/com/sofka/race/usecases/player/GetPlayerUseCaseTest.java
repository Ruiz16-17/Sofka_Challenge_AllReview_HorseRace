package co.com.sofka.race.usecases.player;

import co.com.sofka.race.collections.Player;
import co.com.sofka.race.model.PlayerDTO;
import co.com.sofka.race.reposioties.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class GetPlayerUseCaseTest {

    @SpyBean
    GetPlayerUseCase getPlayerUseCase;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void getPlayerUseCaseTest(){
        var playerDTO = new PlayerDTO("1","player@Email","namePlayer",2,2L);
        var player = new Player();

        player.setId(playerDTO.getId());
        player.setEmail(playerDTO.getEmail());
        player.setName(playerDTO.getName());
        player.setBetCarriel(playerDTO.getBetCarriel());
        player.setMoney(playerDTO.getMoney());

        Mockito.when(playerRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(player));

        var resultDTO = getPlayerUseCase.apply("1");

        Assertions.assertEquals(resultDTO.block().getName(), playerDTO.getName());
        Assertions.assertEquals(resultDTO.block().getEmail(), playerDTO.getEmail());

    }

}