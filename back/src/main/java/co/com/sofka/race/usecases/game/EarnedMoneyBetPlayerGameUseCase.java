package co.com.sofka.race.usecases.game;

import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.usecases.player.GetPlayerUseCase;
import co.com.sofka.race.usecases.player.UpdatePlayerUseCase;
import co.com.sofka.race.usecases.track.GetTrackUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;


@Service
@Validated
public class EarnedMoneyBetPlayerGameUseCase {

    private final GetGameUseCase getGameUseCase;
    private final UpdatePlayerUseCase updatePlayerUseCase;
    private final GetPlayerUseCase getPlayerUseCase;
    private final GetTrackUseCase getTrackUseCase;

    public EarnedMoneyBetPlayerGameUseCase(GetGameUseCase getGameUseCase, UpdatePlayerUseCase updatePlayerUseCase, GetPlayerUseCase getPlayerUseCase, GetTrackUseCase getTrackUseCase) {
        this.getGameUseCase = getGameUseCase;
        this.updatePlayerUseCase = updatePlayerUseCase;
        this.getPlayerUseCase = getPlayerUseCase;
        this.getTrackUseCase = getTrackUseCase;
    }

    public Mono<String> apply(GameDTO gameDTO) {
        return getGameUseCase.apply(gameDTO.getId())
                .flatMap(
                        foundGameDTO -> getPlayerUseCase.apply(foundGameDTO.getIdUser())
                                .flatMap(playerDTO ->

                                        getTrackUseCase.apply(foundGameDTO.getIdTrack())
                                                .flatMap(trackDTO -> {
                                                            playerDTO.setMoney(playerDTO.getMoney() + (foundGameDTO.getMoneyBet()*trackDTO.getLanes().length));
                                                            return updatePlayerUseCase.apply(playerDTO);
                                                        }
                                                ).map(s -> playerDTO.getMoney().toString())
                                )
                );
    }

}
