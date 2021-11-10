package co.com.sofka.questions.routers.favoriteQuestion;

import co.com.sofka.questions.usecases.favoriteQuestion.DeleteFavoriteQuestionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteFavoriteQuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> deleteFavoriteQuestion(DeleteFavoriteQuestionUseCase deleteFavoriteQuestionUseCase) {
        return route(
                DELETE("/deleteFavoriteQuestion/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteFavoriteQuestionUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

}
