package co.com.sofka.questions.usecases.favoriteQuestion;

import co.com.sofka.questions.model.FavoriteQuestionDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.FavoriteQuestionRepository;
import co.com.sofka.questions.usecases.question.ListUseCase;
import co.com.sofka.questions.util.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetFavoriteQuestionOwnerListUseCase {

    private final FavoriteQuestionRepository favoriteQuestionRepository;
    private final ListUseCase listUseCase;
    private final MapperUtils mapperUtils;

    public GetFavoriteQuestionOwnerListUseCase(MapperUtils mapperUtils, FavoriteQuestionRepository favoriteQuestionRepository, ListUseCase listUseCase) {
        this.favoriteQuestionRepository = favoriteQuestionRepository;
        this.mapperUtils = mapperUtils;
        this.listUseCase = listUseCase;
    }

    private Flux<String> getAllFavoriteQuestionsIdByUserId(String userId) {
        return favoriteQuestionRepository.findFavoriteQuestionsByUserId(userId)
                .map(mapperUtils.mapEntityToFavoriteQuestion())
                .map(FavoriteQuestionDTO::getId);

    }

    public Mono<String> getOneFavoriteQuestionIdByUserId(String userId, String questionId) {
        return getAllFavoriteQuestionsIdByUserId(userId)
                .reduce((s, s2) -> String.valueOf(s2.equalsIgnoreCase(questionId))
                );

    }

    public Flux<QuestionDTO> getAllFavoriteQuestionsByUserId(String userId) {
        return listUseCase.getMappersQuestions(userId)
                .filter(QuestionDTO::isFavorite);
    }
}
