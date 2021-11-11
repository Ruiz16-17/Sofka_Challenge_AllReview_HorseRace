package co.com.sofka.race.usecases.game;

import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.usecases.player.GetPlayerUseCase;
import co.com.sofka.race.usecases.player.UpdatePlayerUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class EarnedMoneyBetPlayerGameUseCase {

    private final GetGameUseCase getGameUseCase;
    private final UpdatePlayerUseCase updatePlayerUseCase;
    private final GetPlayerUseCase getPlayerUseCase;

    public EarnedMoneyBetPlayerGameUseCase(GetGameUseCase getGameUseCase, UpdatePlayerUseCase updatePlayerUseCase, GetPlayerUseCase getPlayerUseCase) {
        this.getGameUseCase = getGameUseCase;
        this.updatePlayerUseCase = updatePlayerUseCase;
        this.getPlayerUseCase = getPlayerUseCase;
    }

    public Mono<String> apply(GameDTO gameDTO) {
        return getGameUseCase.apply(gameDTO.getId())
                .flatMap(
                        foundGameDTO -> getPlayerUseCase.apply(foundGameDTO.getIdUser())
                                .flatMap(playerDTO -> {
                                    playerDTO.setMoney(playerDTO.getMoney()+foundGameDTO.getMoneyBet());
                                    return updatePlayerUseCase.apply(playerDTO)
                                            .map(s -> playerDTO.getMoney().toString());
                                })
                );
    }

}
