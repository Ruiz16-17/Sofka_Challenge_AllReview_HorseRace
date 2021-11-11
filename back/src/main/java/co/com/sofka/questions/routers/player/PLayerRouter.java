package co.com.sofka.questions.routers.player;

import co.com.sofka.questions.model.GameDTO;
import co.com.sofka.questions.model.PlayerDTO;
import co.com.sofka.questions.usecases.game.CreateGameUseCase;
import co.com.sofka.questions.usecases.game.GetGameUseCase;
import co.com.sofka.questions.usecases.game.PlayGameUseCase;
import co.com.sofka.questions.usecases.player.CreatePlayerUseCase;
import co.com.sofka.questions.usecases.player.GetPlayerUseCase;
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
public class PLayerRouter {

    @Bean
    public RouterFunction<ServerResponse> createPlayer(CreatePlayerUseCase createPlayerUseCase) {
        Function<PlayerDTO, Mono<ServerResponse>> executor = playerDTO ->  createPlayerUseCase.apply(playerDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/createPlayer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PlayerDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getPlayer(GetPlayerUseCase getPlayerUseCase) {
        return route(
                GET("/getPlayer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getPlayerUseCase.apply(
                                        request.pathVariable("id")),
                                PlayerDTO.class
                        ))
        );
    }

}


