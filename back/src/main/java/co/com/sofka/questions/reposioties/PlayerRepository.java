package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.collections.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {

    Mono<Player> findById(String Id);

}
