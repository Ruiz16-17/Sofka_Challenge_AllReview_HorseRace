package co.com.sofka.race.usecases.horse;

import co.com.sofka.race.model.HorseDTO;
import co.com.sofka.race.reposioties.HorseRepository;
import co.com.sofka.race.util.MapperHorse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetHorseUseCase implements Function<String, Mono<HorseDTO>> {
    private final HorseRepository horseRepository;
    private final MapperHorse mapperHorse;

    public GetHorseUseCase(MapperHorse mapperHorse, HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
        this.mapperHorse = mapperHorse;
    }

    @Override
    public Mono<HorseDTO> apply(String id) {

        return horseRepository.findById(id)
                .map(mapperHorse.mapEntityToHorse());
    }

}
