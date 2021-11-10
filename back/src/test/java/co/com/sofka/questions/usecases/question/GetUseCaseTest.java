package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.FavoriteQuestionRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
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

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class GetUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;
    @SpyBean
    private GetUseCase getQuestion;

    @Test
    public void get(){

        var questionDTO = new QuestionDTO("1","1","¿What is java?", "OPEN","TECHNOLOGY AND COMPUTER", "test@gmail.com");
        var question= new Question();
        question.setId("1");
        question.setQuestion("¿What is java?");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("TECHNOLOGY AND COMPUTER");

        Mockito.when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(question));

        var respuesta = getQuestion.apply("1");
        Assertions.assertEquals(respuesta.block().getQuestion(), question.getQuestion());
    }

}