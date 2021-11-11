package co.com.sofka.race.reposioties;

import co.com.sofka.race.collections.Horse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface HorseRepository extends ReactiveCrudRepository<Horse, String> {

    Mono<Horse> findById(String Id);

}
