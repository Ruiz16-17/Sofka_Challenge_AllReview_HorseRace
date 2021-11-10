package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Horse;
import co.com.sofka.questions.collections.Question;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HorseRepository extends ReactiveCrudRepository<Horse, String> {

    Mono<Horse> findById(String Id);

}
