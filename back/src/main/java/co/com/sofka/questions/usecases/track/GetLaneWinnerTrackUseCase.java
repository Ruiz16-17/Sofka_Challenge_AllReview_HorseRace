package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.model.TrackDTO;
import co.com.sofka.questions.reposioties.TrackRepository;
import co.com.sofka.questions.util.MapperTrack;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetLaneWinnerTrackUseCase  {

    private final GetTrackUseCase getTrackUseCase;

    public GetLaneWinnerTrackUseCase(GetTrackUseCase getTrackUseCase) {
        this.getTrackUseCase = getTrackUseCase;
    }

    private int getLaneWinner(int [][] racecourse, int lane){

        final int km = racecourse[0].length;

        for(int i=0;i<lane;i++){

            if(racecourse[i][km-1] != 0){

                return i;
            }
        }

        return 0;
    }


    public Mono<Integer> apply(String id) {

       return getTrackUseCase.apply(id).map(
               trackDTO -> getLaneWinner(trackDTO.getRacecourse(), trackDTO.getLanes().length)
       );

    }

}