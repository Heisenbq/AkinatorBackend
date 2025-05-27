package flpproject.akinator.repository;

import flpproject.akinator.model.AddedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddedGameRepository extends JpaRepository<AddedGame, Long> {
    List<AddedGame> findByAddedByUser_UserId(Long userId);
}
