package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.when;

@SpringBootTest
class CreateUseCaseTest {
    @SpyBean
    CreateUseCase createUseCase;

    @MockBean
    QuestionRepository questionRepository;

    @Test
    void answerTest(){
        var questionDTO = new QuestionDTO("1","1","question01","tech","tech","test@gmail");
        var question= new Question();
        question.setId("1");
        question.setQuestion("¿What is java?");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("TECHNOLOGY AND COMPUTER");
        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var reusultDTO = createUseCase.apply(questionDTO);
        var resultQuestionDTO = reusultDTO.block();
        assert resultQuestionDTO != null;

        Assertions.assertEquals(resultQuestionDTO,questionDTO.getId());

    }
}