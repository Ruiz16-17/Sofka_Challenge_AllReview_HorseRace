package co.com.sofka.race.usecases.player;

import co.com.sofka.race.model.PlayerDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class MakeBetPlayerUseCase implements SavePlayer {

    private final UpdatePlayerUseCase updatePlayerUseCase;
    private final GetPlayerUseCase getPlayerUseCase;

    public MakeBetPlayerUseCase(UpdatePlayerUseCase updatePlayerUseCase, GetPlayerUseCase getPlayerUseCase) {
        this.updatePlayerUseCase = updatePlayerUseCase;
        this.getPlayerUseCase = getPlayerUseCase;
    }

    @Override
    public Mono<String> apply(PlayerDTO playerDTO) {

        return getPlayerUseCase.apply(playerDTO.getId())
                .flatMap(foundPlayerDTO -> {
                    foundPlayerDTO.setMoney(foundPlayerDTO.getMoney()-Math.abs(playerDTO.getMoneyBet()));
                    return updatePlayerUseCase.apply(foundPlayerDTO)
                            .map(s -> foundPlayerDTO.getMoney().toString());
                });

    }

}
