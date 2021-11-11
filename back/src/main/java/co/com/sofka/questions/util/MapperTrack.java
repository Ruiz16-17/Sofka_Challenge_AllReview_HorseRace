package co.com.sofka.questions.util;

import co.com.sofka.questions.collections.Track;
import co.com.sofka.questions.model.TrackDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperTrack {

    public Function<TrackDTO, Track> mapperToTrack(String id) {
        return trackDTO -> {
            var track = new Track();
            track.setId(id);
            track.setKm(trackDTO.getKm());
            track.setLanes(trackDTO.getLanes());
            track.setName(trackDTO.getName());
            track.setRacecourse(trackDTO.getRacecourse());
            track.setCompleted(trackDTO.isCompleted());
            track.setLaneWinner(trackDTO.getLaneWinner());
            return track;
        };
    }

    public Function<Track, TrackDTO> mapEntityToTrack() {
        return entity -> new TrackDTO(
                entity.getId(),
                entity.getName(),
                entity.getKm(),
                entity.getLanes(),
                entity.getRacecourse(),
                entity.isCompleted(),
                entity.getLaneWinner()
        );
    }

}
