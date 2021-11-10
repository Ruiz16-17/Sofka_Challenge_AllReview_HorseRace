package co.com.sofka.questions.usecases.answer;

import co.com.sofka.questions.service.SendMailService;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.usecases.question.GetUseCase;
import co.com.sofka.questions.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {

    @Autowired
    private final SendMailService sendMailService;

    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;

    public AddAnswerUseCase(SendMailService sendMailService, MapperUtils mapperUtils, GetUseCase getUseCase, AnswerRepository answerRepository) {
        this.sendMailService = sendMailService;
        this.answerRepository = answerRepository;
        this.getUseCase = getUseCase;
        this.mapperUtils = mapperUtils;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the answer is required");
        return getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer(null).apply(answerDTO))
                        .map(answer -> {
                            question.getAnswers().add(answerDTO);
                            sendMailService.sendMail(
                                    question.getUserEmail(),
                                    "Has answered your question: " + question.getQuestion(),
                                    "Answer: \n" + answer.getAnswer());
                            return question;
                        })
        );
    }

}
