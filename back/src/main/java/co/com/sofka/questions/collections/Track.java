package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Track {

    @Id
    private String id;
    private String name;
    private int km;
    private int[] lanes;
    private int[][] racecourse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int[] getLanes() {
        return lanes;
    }

    public void setLanes(int[] lanes) {
        this.lanes = lanes;
    }

    public int[][] getRacecourse() {
        return racecourse;
    }

    public void setRacecourse(int[][] racecourse) {
        this.racecourse = racecourse;
    }
}
