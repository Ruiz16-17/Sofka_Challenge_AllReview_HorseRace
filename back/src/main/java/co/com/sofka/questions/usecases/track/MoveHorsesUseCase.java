package co.com.sofka.questions.usecases.track;

import co.com.sofka.questions.model.TrackDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class MoveHorsesUseCase {

    private final UpdateTrackUseCase updateTrackUseCase;
    private final GetTrackUseCase getTrackUseCase;

    public MoveHorsesUseCase(UpdateTrackUseCase updateTrackUseCase, GetTrackUseCase getTrackUseCase) {
        this.updateTrackUseCase = updateTrackUseCase;
        this.getTrackUseCase = getTrackUseCase;
    }

    private int randomNumber(){
        return (int) Math.floor(Math.random()*6+1);
    }

    private int getPosition(int[][] racecourse, int lane){
        int position = 0;

        for(int i=0;i<racecourse[0].length;i++){

            if(racecourse[lane-1][i] == lane){
                position = i;

                break;
            }
        }

        return position;
    }

    private boolean isTrackCompleted(int [][] racecourse, int lane){

        final int km = racecourse[0].length;

        for(int i=0;i<lane;i++){

            if(racecourse[i][km-1] != 0){

                return true;
            }
        }

        return false;
    }

    private TrackDTO moveHorses(TrackDTO trackDTO){
        final int lanes = trackDTO.getLanes().length;
        final int km = trackDTO.getKm()*10;

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
                trackDTO.setLaneWinner(i);
                System.out.println("Finalizado");
            }
        }

        trackDTO.setRacecourse(racecourse);

        if(isTrackCompleted(racecourse,lanes)){
            trackDTO.setCompleted(true);
        }

        return trackDTO;
    }

    public Mono<String> apply(String idTrack) {
        return getTrackUseCase.apply(idTrack).flatMap(
                foundTrackDTO -> updateTrackUseCase.apply(moveHorses(foundTrackDTO))

        );
    }

}
