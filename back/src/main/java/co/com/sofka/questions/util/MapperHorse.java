package co.com.sofka.questions.util;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.FavoriteQuestion;
import co.com.sofka.questions.collections.Horse;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.FavoriteQuestionDTO;
import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperHorse {

    public Function<HorseDTO, Horse> mapperToHorse(String id) {
        return updateHorse -> {
            var horse = new Horse();
            horse.setId(id);
            horse.setPosition(updateHorse.getPosition());
            horse.setColor(updateHorse.getColor());
            horse.setName(updateHorse.getName());

            return horse;
        };
    }

    public Function<Horse, HorseDTO> mapEntityToHorse() {
        return entity -> new HorseDTO(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getPosition()
        );
    }

}
