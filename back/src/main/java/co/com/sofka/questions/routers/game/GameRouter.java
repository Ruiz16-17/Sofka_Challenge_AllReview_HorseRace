package co.com.sofka.questions.routers.game;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.usecases.game.CreateGameUseCase;
import co.com.sofka.questions.usecases.game.GetGameUseCase;
import co.com.sofka.questions.usecases.game.PlayGameUseCase;
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
public class GameRouter {

    @Bean
    public RouterFunction<ServerResponse> createGame(CreateGameUseCase createGameUseCase) {
        Function<GameDTO, Mono<ServerResponse>> executor = gameDTO ->  createGameUseCase.apply(gameDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createGame").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(GameDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getGame(GetGameUseCase getGameUseCase) {
        return route(
                GET("/getGame/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getGameUseCase.apply(
                                        request.pathVariable("id")),
                                GameDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> playGame(PlayGameUseCase playGameUseCase) {
        Function<GameDTO, Mono<ServerResponse>> executor = gameDTO ->  playGameUseCase.playGame(gameDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                PUT("/playGame").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(GameDTO.class).flatMap(executor)
        );
    }

}


