package flpproject.akinator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "added_games")
public class AddedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long addedGameId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "added_by_user_id", nullable = false)
    private User addedByUser;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public Long getAddedGameId() {
        return addedGameId;
    }

    public void setAddedGameId(Long addedGameId) {
        this.addedGameId = addedGameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
