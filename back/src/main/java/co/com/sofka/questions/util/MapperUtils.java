package co.com.sofka.questions.util;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.FavoriteQuestion;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.FavoriteQuestionDTO;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<AnswerDTO, Answer> mapperToAnswer(String id) {
        return updateAnswer -> {
            var answer = new Answer();
            answer.setId(id);
            answer.setPosition(updateAnswer.getPosition());
            answer.setQuestionId(updateAnswer.getQuestionId());
            answer.setUserId(updateAnswer.getUserId());
            answer.setAnswer(updateAnswer.getAnswer());
            return answer;
        };
    }

    public Function<Answer, AnswerDTO> mapEntityToAnswer() {
        return entity -> new AnswerDTO(
                entity.getQuestionId(),
                entity.getUserId(),
                entity.getAnswer(),
                entity.getPosition(),
                entity.getId()
        );
    }

    public Function<QuestionDTO, Question> mapperToQuestion(String id) {
        return updateQuestion -> {
            var question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setCategory(updateQuestion.getCategory());
            question.setQuestion(updateQuestion.getQuestion());
            question.setType(updateQuestion.getType());
            question.setUserEmail(updateQuestion.getUserEmail());
            return question;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion() {
        return entity -> new QuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestion(),
                entity.getType(),
                entity.getCategory(),
                entity.getUserEmail()
        );
    }

    public Function<FavoriteQuestionDTO, FavoriteQuestion> mapperToFavoriteQuestion(String id) {
        return updateFavoriteQuestion -> {
            var favoriteQuestion = new FavoriteQuestion();
            favoriteQuestion.setId(id);
            favoriteQuestion.setUserId(updateFavoriteQuestion.getUserId());
            favoriteQuestion.setQuestionId(updateFavoriteQuestion.getQuestionId());
            favoriteQuestion.setFavorite(updateFavoriteQuestion.isFavorite());
            return favoriteQuestion;
        };
    }

    public Function<FavoriteQuestion, FavoriteQuestionDTO> mapEntityToFavoriteQuestion() {
        return entity -> new FavoriteQuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestionId(),
                entity.isFavorite()
        );
    }

}
