package co.com.sofka.race.reposioties;

import co.com.sofka.race.collections.Game;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveCrudRepository<Game, String> {

    Mono<Game> findById(String Id);

}
