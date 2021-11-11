package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Game;
import co.com.sofka.questions.collections.Track;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveCrudRepository<Game, String> {

    Mono<Game> findById(String Id);

}
