package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuestionDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String question;
    @NotBlank
    private String type;
    @NotBlank
    private String category;
    @NotBlank
    private String userEmail;

    private boolean isFavorite;

    private String favoriteQuestionId;

    private List<AnswerDTO> answers;

    public QuestionDTO() {

    }

    public QuestionDTO(String userId, String question, String type, String category, String userEmail) {
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.userEmail = userEmail;
    }

    public QuestionDTO(String id, String userId, String question, String type, String category, String userEmail) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.userEmail = userEmail;
    }

    public List<AnswerDTO> getAnswers() {
        this.answers = Optional.ofNullable(answers).orElse(new ArrayList<>());
        return answers;
    }

    public boolean isFavorite() {
        this.isFavorite = Optional.ofNullable(this.isFavorite).orElse(false);
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getFavoriteQuestionId() {
        return favoriteQuestionId = Optional.ofNullable(this.favoriteQuestionId).orElse("");
    }

    public void setFavoriteQuestionId(String favoriteQuestionId) {
        this.favoriteQuestionId = favoriteQuestionId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(question, that.question) && Objects.equals(type, that.type) && Objects.equals(category, that.category) && Objects.equals(userEmail, that.userEmail) && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, question, type, category, userEmail, answers);
    }
}
