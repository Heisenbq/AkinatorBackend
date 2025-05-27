package flpproject.akinator.service;

import flpproject.akinator.model.AddedQuestion;
import flpproject.akinator.repository.AddedQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddedQuestionService {
    private final AddedQuestionRepository addedQuestionRepository;

    public AddedQuestionService(AddedQuestionRepository addedQuestionRepository) {
        this.addedQuestionRepository = addedQuestionRepository;
    }

    public List<AddedQuestion> findAll() {
        return addedQuestionRepository.findAll();
    }

    public Optional<AddedQuestion> findById(Long id) {
        return addedQuestionRepository.findById(id);
    }

    public AddedQuestion save(AddedQuestion addedQuestion) {
        return addedQuestionRepository.save(addedQuestion);
    }

    public void deleteById(Long id) {
        addedQuestionRepository.deleteById(id);
    }
    public List<AddedQuestion> getAddedQuestionsByUserId(Long userId) {
        return addedQuestionRepository.findByAddedByUser_UserId(userId);
    }
}
