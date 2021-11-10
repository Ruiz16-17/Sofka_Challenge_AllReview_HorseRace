package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;

public class HorseDTO {

    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String color;
    private int position;

    public HorseDTO() {
    }

    public HorseDTO(String name, String color, int position) {
        this.name = name;
        this.color = color;
        this.position = position;
    }

    public HorseDTO(String id, String name, String color, int position) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.position = position;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
