package flpproject.akinator.service;

import flpproject.akinator.model.AddedGame;
import flpproject.akinator.repository.AddedGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddedGameService {
    private final AddedGameRepository addedGameRepository;

    public AddedGameService(AddedGameRepository addedGameRepository) {
        this.addedGameRepository = addedGameRepository;
    }

    public List<AddedGame> findAll() {
        return addedGameRepository.findAll();
    }

    public Optional<AddedGame> findById(Long id) {
        return addedGameRepository.findById(id);
    }

    public AddedGame save(AddedGame addedGame) {
        return addedGameRepository.save(addedGame);
    }

    public void deleteById(Long id) {
        addedGameRepository.deleteById(id);
    }

    public List<AddedGame> getAddedGamesByUserId(Long userId) {
        return addedGameRepository.findByAddedByUser_UserId(userId);
    }
}
