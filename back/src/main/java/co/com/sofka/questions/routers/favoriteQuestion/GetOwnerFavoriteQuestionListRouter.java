package co.com.sofka.questions.routers.favoriteQuestion;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.favoriteQuestion.GetFavoriteQuestionOwnerListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetOwnerFavoriteQuestionListRouter {

    @Bean
    public RouterFunction<ServerResponse> getOwnerAllFavoriteQuestion(GetFavoriteQuestionOwnerListUseCase getFavoriteQuestionOwnerListUseCase) {
        return route(
                GET("/getOwnerAllFavoriteQuestion/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getFavoriteQuestionOwnerListUseCase.getAllFavoriteQuestionsByUserId(request.pathVariable("userId")),
                                QuestionDTO.class
                        ))
        );
    }

}
