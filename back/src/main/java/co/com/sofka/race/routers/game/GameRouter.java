package co.com.sofka.race.routers.game;

import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.usecases.game.*;
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

    @Bean
    public RouterFunction<ServerResponse> earnedMoneyBetPlayer(EarnedMoneyBetPlayerGameUseCase earnedMoneyBetPlayerGameUseCase) {
        Function<GameDTO, Mono<ServerResponse>> executor = gameDTO ->  earnedMoneyBetPlayerGameUseCase.apply(gameDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                PUT("/earnedMoneyBetPlayer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(GameDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> winnerGamePlayer(WinnerGameUseCase winnerGameUseCase) {
        return route(
                GET("/winnerGamePlayer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(winnerGameUseCase.apply(
                                        request.pathVariable("id")),
                                String.class
                        ))
        );
    }

}


