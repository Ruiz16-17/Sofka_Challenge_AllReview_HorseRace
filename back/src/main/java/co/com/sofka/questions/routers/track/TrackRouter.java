package co.com.sofka.questions.routers.track;

import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.usecases.horse.CreateHorseUseCase;
import co.com.sofka.questions.usecases.horse.GetHorseUseCase;
import co.com.sofka.questions.usecases.horse.UpdateHorseUseCase;
import co.com.sofka.questions.usecases.horse.UpdatePositionHorseUseCase;
import co.com.sofka.questions.usecases.track.CreateTrackUseCase;
import co.com.sofka.questions.usecases.track.GetTrackUseCase;
import co.com.sofka.questions.usecases.track.MoveHorsesUseCase;
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
        Function<TrackDTO, Mono<ServerResponse>> executor = trackDTO ->  moveHorsesUseCase.apply(trackDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/moveHorse").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TrackDTO.class).flatMap(executor)
        );
    }
}


