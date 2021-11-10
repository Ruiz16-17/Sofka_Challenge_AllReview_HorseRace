package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.collections.FavoriteQuestion;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.FavoriteQuestionRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.util.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class ListUseCase implements Supplier<Flux<QuestionDTO>> {

    private final FavoriteQuestionRepository favoriteQuestionRepository;
    private final QuestionRepository questionRepository;
    private final MapperUtils mapperUtils;

    public ListUseCase(FavoriteQuestionRepository favoriteQuestionRepository, MapperUtils mapperUtils, QuestionRepository questionRepository) {
        this.favoriteQuestionRepository = favoriteQuestionRepository;
        this.questionRepository = questionRepository;
        this.mapperUtils = mapperUtils;
    }

    public Flux<QuestionDTO> getMappersQuestions(String id) {
        return get().flatMap(mapFavoriteQuestionAggregate(id));
    }


    private Function<QuestionDTO, Flux<QuestionDTO>> mapFavoriteQuestionAggregate(String id) {
        return questionDTO ->
                Flux.just(questionDTO).zipWith(
                        favoriteQuestionRepository.findFavoriteQuestionsByUserId(id)
                                .collectList()
                        ,
                        (question, favoriteQuestions) -> {
                            Long count = favoriteQuestions
                                    .stream()
                                    .filter(favoriteQuestion -> favoriteQuestion.getQuestionId()
                                            .equalsIgnoreCase(questionDTO.getId())).count();

                            Optional<String> idFavoriteQuestion = favoriteQuestions
                                    .stream()
                                    .filter(favoriteQuestion ->
                                    favoriteQuestion.getQuestionId().equalsIgnoreCase(questionDTO.getId()))
                                    .map(FavoriteQuestion::getId)
                                    .findFirst();

                            if (count > 0) {
                                question.setFavorite(true);
                                question.setFavoriteQuestionId(idFavoriteQuestion.orElse(""));
                            }

                            return question;
                        }
                );
    }

    @Override
    public Flux<QuestionDTO> get() {
        return questionRepository.findAll()
                .map(mapperUtils.mapEntityToQuestion());
    }

}
