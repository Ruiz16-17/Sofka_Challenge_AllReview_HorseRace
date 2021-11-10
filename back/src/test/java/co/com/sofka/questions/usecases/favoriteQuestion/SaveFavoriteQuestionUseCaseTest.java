package co.com.sofka.questions.usecases.favoriteQuestion;

import co.com.sofka.questions.collections.FavoriteQuestion;
import co.com.sofka.questions.model.FavoriteQuestionDTO;
import co.com.sofka.questions.reposioties.FavoriteQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class SaveFavoriteQuestionUseCaseTest {

    @SpyBean
    SaveFavoriteQuestionUseCase saveFavoriteQuestionUseCase;

    @MockBean
    FavoriteQuestionRepository favoriteQuestionRepository;

    @Test
    void answerTest(){
        var favoriteQuestionDTO = new FavoriteQuestionDTO("1","1","1",true);
        var favoriteQuestion = new FavoriteQuestion();
        favoriteQuestion.setId("1");
        favoriteQuestion.setUserId("1");
        Mockito.when(favoriteQuestionRepository.save(Mockito.any(FavoriteQuestion.class))).thenReturn(Mono.just(favoriteQuestion));

        var reusultDTO = saveFavoriteQuestionUseCase.apply(favoriteQuestionDTO);
        var resultQuestionDTO = reusultDTO.block();
        assert resultQuestionDTO != null;

        Assertions.assertEquals(resultQuestionDTO,favoriteQuestionDTO.getId());

    }
}