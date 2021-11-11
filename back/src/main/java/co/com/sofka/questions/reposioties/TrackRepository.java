package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Horse;
import co.com.sofka.questions.collections.Track;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TrackRepository extends ReactiveCrudRepository<Track, String> {

    Mono<Track> findById(String Id);

}
