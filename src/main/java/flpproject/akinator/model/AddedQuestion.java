package flpproject.akinator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "added_questions")
public class AddedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long addedQuestionId;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "added_by_user_id", nullable = false)
    private User addedByUser;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public Long getAddedQuestionId() {
        return addedQuestionId;
    }

    public void setAddedQuestionId(Long addedQuestionId) {
        this.addedQuestionId = addedQuestionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAddedByUser() {
        return addedByUser;
    }

    public void setAddedByUser(User addedByUser) {
        this.addedByUser = addedByUser;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
