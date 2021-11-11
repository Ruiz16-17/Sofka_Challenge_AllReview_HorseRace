package co.com.sofka.race.routers.track;

import co.com.sofka.race.model.TrackDTO;
import co.com.sofka.race.usecases.track.CreateTrackUseCase;
import co.com.sofka.race.usecases.track.GetTrackUseCase;
import co.com.sofka.race.usecases.track.MoveHorsesUseCase;
import co.com.sofka.race.usecases.track.RestartTrackUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TrackRouter {

    @Bean
    public RouterFunction<ServerResponse> createTrack(CreateTrackUseCase createTrackUseCase) {
        Function<TrackDTO, Mono<ServerResponse>> executor = trackDTO ->  createTrackUseCase.apply(trackDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createTrack").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TrackDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getTrack(GetTrackUseCase getTrackUseCase) {
        return route(
                GET("/getTrack/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTrackUseCase.apply(
                                        request.pathVariable("id")),
                                TrackDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> moveHorse(MoveHorsesUseCase moveHorsesUseCase) {
        Function<TrackDTO, Mono<ServerResponse>> executor = trackDTO ->  moveHorsesUseCase.apply(trackDTO.getId())
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/moveHorse").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TrackDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> restartTrack(RestartTrackUseCase restartTrackUseCase) {

        return route(
                PUT("/restartTrack/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(restartTrackUseCase.apply(
                                request.pathVariable("id")
                        ),String.class))
        );
    }
}


