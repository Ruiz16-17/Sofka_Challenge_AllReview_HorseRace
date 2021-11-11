package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Game {

    @Id
    private String id;
    private String idTrack;
    private String idUser;
    private boolean inGame;
    private int laneWinner;
    private Long moneyBet;

    public Long getMoneyBet() {
        return moneyBet;
    }

    public void setMoneyBet(Long moneyBet) {
        this.moneyBet = moneyBet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(String idTrack) {
        this.idTrack = idTrack;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getLaneWinner() {
        return laneWinner;
    }

    public void setLaneWinner(int laneWinner) {
        this.laneWinner = laneWinner;
    }
}
