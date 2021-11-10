package co.com.sofka.questions.usecases.favoriteQuestion;

import co.com.sofka.questions.model.AnswerDTO;
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
class DeleteFavoriteQuestionUseCaseTest {
    @SpyBean
    DeleteFavoriteQuestionUseCase deleteFavoriteQuestionUseCase;

    @MockBean
    FavoriteQuestionRepository favoriteQuestionRepository;

    @Test
    void deleteTest(){
        var favoriteQuestionDTO = new FavoriteQuestionDTO("1","1","1",true);
        Mockito.when(favoriteQuestionRepository.deleteById(favoriteQuestionDTO.getId())).thenReturn(Mono.empty());

        var dataEmpty = deleteFavoriteQuestionUseCase.apply(favoriteQuestionDTO.getId()).thenReturn(Mono.empty());

        Assertions.assertEquals(dataEmpty.block(),Mono.empty());
    }
}