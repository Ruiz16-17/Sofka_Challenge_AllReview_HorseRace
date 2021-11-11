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
class CreatePlayerUseCaseTest {

    @SpyBean
    CreatePlayerUseCase createPlayerUseCase;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void createPlayerUseCaseTest(){
        var playerDTO = new PlayerDTO("1","player@Email","namePlayer",2,2L);
        var player = new Player();

        player.setId(playerDTO.getId());
        player.setEmail(playerDTO.getEmail());
        player.setName(playerDTO.getName());
        player.setBetCarriel(playerDTO.getBetCarriel());
        player.setMoney(playerDTO.getMoney());

        Mockito.when(playerRepository.save(Mockito.any(Player.class))).thenReturn(Mono.just(player));

        var resultDTO = createPlayerUseCase.apply(playerDTO);
        var resultPlayerDTO = resultDTO.block();

        assert resultPlayerDTO != null;

        Assertions.assertEquals(resultPlayerDTO, playerDTO.getId());

    }

}