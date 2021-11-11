package co.com.sofka.questions.routers.horse;

import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.usecases.horse.CreateHorseUseCase;
import co.com.sofka.questions.usecases.horse.GetHorseUseCase;
import co.com.sofka.questions.usecases.horse.UpdateHorseUseCase;
import co.com.sofka.questions.usecases.horse.UpdatePositionHorseUseCase;
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
public class HorseRouter {

    @Bean
    public RouterFunction<ServerResponse> createHorse(CreateHorseUseCase createHorseUseCase) {
        Function<HorseDTO, Mono<ServerResponse>> executor = horseDTO ->  createHorseUseCase.apply(horseDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createHorse").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(HorseDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateHorse(UpdateHorseUseCase updateHorseUseCase) {
        Function<HorseDTO, Mono<ServerResponse>> executor = horseDTO ->  updateHorseUseCase.apply(horseDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                PUT("/updateHorse").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(HorseDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updatePositionHorse(UpdatePositionHorseUseCase updatePositionHorseUseCase) {
        Function<HorseDTO, Mono<ServerResponse>> executor = horseDTO ->  updatePositionHorseUseCase.apply(horseDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/updatePositionHorse").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(HorseDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getHorse(GetHorseUseCase getHorseUseCase) {
        return route(
                GET("/getHorse/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getHorseUseCase.apply(
                                        request.pathVariable("id")),
                                HorseDTO.class
                        ))
        );
    }
}


