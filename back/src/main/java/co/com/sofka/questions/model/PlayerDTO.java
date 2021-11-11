package co.com.sofka.questions.model;

public class PlayerDTO {

    private String id;
    private String email;
    private String name;
    private int betCarriel;
    private Long money;
    private Long moneyBet;

    public PlayerDTO() {
    }

    public PlayerDTO(String email, String name, int betCarriel, Long money) {
        this.email = email;
        this.name = name;
        this.betCarriel = betCarriel;
        this.money = money;
    }

    public PlayerDTO(String id, String email, String name, int betCarriel, Long money) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.betCarriel = betCarriel;
        this.money = money;
    }

    public Long getMoneyBet() {
        return moneyBet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBetCarriel() {
        return betCarriel;
    }

    public void setBetCarriel(int betCarriel) {
        this.betCarriel = betCarriel;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
