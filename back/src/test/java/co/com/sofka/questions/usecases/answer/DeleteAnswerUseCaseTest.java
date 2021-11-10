package co.com.sofka.questions.usecases.answer;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecases.question.ListUseCase;
import co.com.sofka.questions.util.MapperUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeleteAnswerUseCaseTest {

    @SpyBean
    DeleteAnswerUseCase deleteAnswerUseCase;

    @MockBean
    AnswerRepository answerRepository;

    @Test
    void deleteTest(){
        var answerDTO = new AnswerDTO("01","01","anmswer",1,"xx");
        Mockito.when(answerRepository.deleteById(answerDTO.getId())).thenReturn(Mono.empty());

        var dataEmpty = deleteAnswerUseCase.apply(answerDTO.getId()).thenReturn(Mono.empty());

        Assertions.assertEquals(dataEmpty.block(),Mono.empty());
    }

}