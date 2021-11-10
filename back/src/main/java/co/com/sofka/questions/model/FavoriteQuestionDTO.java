package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;

public class FavoriteQuestionDTO {

    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String questionId;
    private boolean isFavorite;

    public FavoriteQuestionDTO() {

    }

    public FavoriteQuestionDTO(String id, String userId, String questionId, boolean isFavorite) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.isFavorite = isFavorite;
    }

    public FavoriteQuestionDTO(String userId, String questionId, boolean isFavorite) {
        this.userId = userId;
        this.questionId = questionId;
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
