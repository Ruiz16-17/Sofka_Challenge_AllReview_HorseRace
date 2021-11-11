package co.com.sofka.race.reposioties;

import co.com.sofka.race.collections.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {

    Mono<Player> findById(String Id);

}
