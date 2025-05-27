package flpproject.akinator.repository;

import flpproject.akinator.model.AliasGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AliasGameRepository extends JpaRepository<AliasGame, Long> {
    List<AliasGame> findBySession_SessionIdAndUser_UserId(Long sessionId, Long userId);
    List<AliasGame> findByUser_UserId(Long userId);

}