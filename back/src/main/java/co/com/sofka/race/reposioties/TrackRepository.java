package co.com.sofka.race.reposioties;

import co.com.sofka.race.collections.Track;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TrackRepository extends ReactiveCrudRepository<Track, String> {

    Mono<Track> findById(String Id);

}
