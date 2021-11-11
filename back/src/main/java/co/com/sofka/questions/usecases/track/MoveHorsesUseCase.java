package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.reposioties.TrackRepository;
import co.com.sofka.questions.util.MapperTrack;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.yaml.snakeyaml.util.ArrayUtils;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Validated
public class MoveHorsesUseCase {

    private final UpdateTrackUseCase updateTrackUseCase;
    private final GetTrackUseCase getTrackUseCase;

    public MoveHorsesUseCase(UpdateTrackUseCase updateTrackUseCase, GetTrackUseCase getTrackUseCase) {
        this.updateTrackUseCase = updateTrackUseCase;
        this.getTrackUseCase = getTrackUseCase;
    }

    public int randomNumber(){
        return (int) Math.floor(Math.random()*6+1);
    }

    public int getPosition(int[][] racecourse, int lane){
        int position = 0;

        for(int i=0;i<racecourse[0].length;i++){

            if(racecourse[lane-1][i] == lane){
                position = i;

                break;
            }
        }

        return position;
    }

    public TrackDTO moveHorses(TrackDTO trackDTO){

        int lanes = trackDTO.getLanes().length;
        int km = trackDTO.getKm()*10;

        int[][] racecourse = new int[lanes][km];

        int position = 0;

        for (int i=0; i<trackDTO.getLanes().length; i++){

            position = getPosition(trackDTO.getRacecourse(),trackDTO.getLanes()[i]);
            position += randomNumber();

            if(position <= km-1){

                racecourse[i][position] = trackDTO.getLanes()[i];
            }
            if(position >= km-1){
                racecourse[lanes-1][km-1] = trackDTO.getLanes()[i];
            }
        }

        trackDTO.setRacecourse(racecourse);

        return trackDTO;
    }

    public Mono<String> apply(TrackDTO trackDTO) {

        return getTrackUseCase.apply(trackDTO.getId()).flatMap(
                foundTrackDTO -> updateTrackUseCase.apply(moveHorses(foundTrackDTO))
        );
    }

}
