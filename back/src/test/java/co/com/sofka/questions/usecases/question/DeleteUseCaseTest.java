package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class DeleteUseCaseTest {

    @SpyBean
    DeleteUseCase deleteUseCase;

    @MockBean
    QuestionRepository questionRepository;

    @Test
    void deleteTest(){
        var questionDTO = new QuestionDTO("1","01","question","tech","tech","test@gmail.com");
        Mockito.when(questionRepository.deleteById(questionDTO.getId())).thenReturn(Mono.empty());

        var dataEmpty = deleteUseCase.apply(questionDTO.getId()).thenReturn(Mono.empty());

        Assertions.assertEquals(dataEmpty.block(),Mono.empty());
    }

}