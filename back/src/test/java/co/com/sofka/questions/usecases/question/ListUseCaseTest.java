package co.com.sofka.questions.usecases.question;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.FavoriteQuestionRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.util.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ListUseCaseTest {

    QuestionRepository questionRepository;
    FavoriteQuestionRepository favoriteQuestionRepository;
    ListUseCase listUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        listUseCase = new ListUseCase(favoriteQuestionRepository, mapperUtils, questionRepository);
    }

    @Test
    void getValidationTest(){
        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setUserEmail("prueba@gmail.com");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        when(questionRepository.findAll()).thenReturn(Flux.just(question ));

        StepVerifier.create(listUseCase.get())
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getUserEmail().equals("prueba@gmail.com");
                    assert questionDTO.getType().equals("tech");
                    return true;
                })
                .verifyComplete();

        verify(questionRepository).findAll();
    }



}