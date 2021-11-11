package co.com.sofka.race.usecases.track;

import co.com.sofka.race.collections.Game;
import co.com.sofka.race.collections.Track;
import co.com.sofka.race.model.GameDTO;
import co.com.sofka.race.model.TrackDTO;
import co.com.sofka.race.reposioties.TrackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class GetTrackUseCaseTest {

    @SpyBean
    GetTrackUseCase getTrackUseCase;

    @MockBean
    TrackRepository trackRepository;

    @Test
    void getTrackUseCaseTest(){

        int[] lanes = {1,2,3};
        int[][] racecourse = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
        var trackDTO = new TrackDTO("1","trackName",1,lanes,racecourse,false,-1);
        var track = new Track();

        track.setId(trackDTO.getId());
        track.setName(trackDTO.getName());
        track.setKm(trackDTO.getKm());
        track.setLanes(trackDTO.getLanes());
        track.setRacecourse(trackDTO.getRacecourse());
        track.setCompleted(trackDTO.isCompleted());
        track.setLaneWinner(trackDTO.getLaneWinner());

        Mockito.when(trackRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(track));

        var resultDTO = getTrackUseCase.apply("1");

        Assertions.assertEquals(resultDTO.block().getName(), trackDTO.getName());
        Assertions.assertEquals(resultDTO.block().isCompleted(), trackDTO.isCompleted());

    }
}