package co.com.sofka.questions.model;

public class GameDTO {

    private String id;
    private String idTrack;
    private String idUser;
    private boolean inGame;
    private int laneWinner;

    public GameDTO() {
    }

    public GameDTO(String idTrack, String idUser, boolean inGame, int laneWinner) {
        this.idTrack = idTrack;
        this.idUser = idUser;
        this.inGame = inGame;
        this.laneWinner = laneWinner;
    }

    public GameDTO(String id, String idTrack, String idUser, boolean inGame, int laneWinner) {
        this.id = id;
        this.idTrack = idTrack;
        this.idUser = idUser;
        this.inGame = inGame;
        this.laneWinner = laneWinner;
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
