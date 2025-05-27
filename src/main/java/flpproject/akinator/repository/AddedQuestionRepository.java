package flpproject.akinator.repository;

import flpproject.akinator.model.AddedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddedQuestionRepository extends JpaRepository<AddedQuestion, Long> {
    List<AddedQuestion> findByAddedByUser_UserId(Long userId);
}
