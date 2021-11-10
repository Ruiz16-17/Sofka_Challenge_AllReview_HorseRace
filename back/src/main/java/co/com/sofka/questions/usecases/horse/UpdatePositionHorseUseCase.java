package co.com.sofka.questions.usecases.horse;

import co.com.sofka.questions.model.HorseDTO;
import co.com.sofka.questions.reposioties.HorseRepository;
import co.com.sofka.questions.util.MapperHorse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdatePositionHorseUseCase implements UpdatePositionHorse{
    private final HorseRepository horseRepository;
    private final MapperHorse mapperHorse;
    private final GetHorseUseCase getHorseUseCase;

    public UpdatePositionHorseUseCase(MapperHorse mapperHorse, HorseRepository horseRepository, GetHorseUseCase getHorseUseCase) {
        this.horseRepository = horseRepository;
        this.mapperHorse = mapperHorse;
        this.getHorseUseCase = getHorseUseCase;
    }

    public int randomNumber(){
        return (int) Math.floor(Math.random()*6+1);
    }

    @Override
    public Mono<HorseDTO> apply(HorseDTO horseDTO) {

        return getHorseUseCase.apply(horseDTO.getId()).flatMap(
                horseDTOFound -> {
                    horseDTO.setPosition(horseDTOFound.getPosition()+randomNumber());

                    return horseRepository.save(mapperHorse.mapperToHorse(horseDTO.getId()).apply(horseDTO))
                            .map(mapperHorse.mapEntityToHorse());

                }
        );
    }

}
