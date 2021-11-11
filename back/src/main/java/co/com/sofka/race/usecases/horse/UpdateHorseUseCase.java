package co.com.sofka.race.usecases.horse;

import co.com.sofka.race.collections.Horse;
import co.com.sofka.race.model.HorseDTO;
import co.com.sofka.race.reposioties.HorseRepository;
import co.com.sofka.race.util.MapperHorse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateHorseUseCase implements SaveHorse {
    private final HorseRepository horseRepository;
    private final MapperHorse mapperHorse;

    public UpdateHorseUseCase(MapperHorse mapperHorse, HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
        this.mapperHorse = mapperHorse;
    }

    @Override
    public Mono<String> apply(HorseDTO horseDTO) {
        return horseRepository
                .save(mapperHorse.mapperToHorse(horseDTO.getId()).apply(horseDTO))
                .map(Horse::getId);
    }

}
