package co.com.sofka.questions.usecases.horse;

import co.com.sofka.questions.collections.Horse;
import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.reposioties.HorseRepository;
import co.com.sofka.questions.util.MapperHorse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateHorseUseCase implements SaveHorse {
    private final HorseRepository horseRepository;
    private final MapperHorse mapperHorse;

    public CreateHorseUseCase(MapperHorse mapperHorse, HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
        this.mapperHorse = mapperHorse;
    }

    @Override
    public Mono<String> apply(HorseDTO horseDTO) {

        return horseRepository
                .save(mapperHorse.mapperToHorse(null).apply(horseDTO))
                .map(Horse::getId);
    }

}
