package co.com.sofka.race.util;

import co.com.sofka.race.collections.Horse;
import co.com.sofka.race.model.HorseDTO;
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
