package flpproject.akinator.service;

import flpproject.akinator.model.AliasGame;
import flpproject.akinator.repository.AliasGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AliasGameService {
    private final AliasGameRepository aliasGameRepository;

    public AliasGameService(AliasGameRepository aliasGameRepository) {
        this.aliasGameRepository = aliasGameRepository;
    }

    public List<AliasGame> findAll() {
        return aliasGameRepository.findAll();
    }

    public Optional<AliasGame> findById(Long id) {
        return aliasGameRepository.findById(id);
    }

    public AliasGame save(AliasGame aliasGame) {
        return aliasGameRepository.save(aliasGame);
    }

    public void deleteById(Long id) {
        aliasGameRepository.deleteById(id);
    }

    public List<AliasGame> getBySessionAndUser(Long sessionId, Long userId) {
        return aliasGameRepository.findBySession_SessionIdAndUser_UserId(sessionId, userId);
    }

    public List<AliasGame> getByUserId(Long userId) {
        return aliasGameRepository.findByUser_UserId(userId);
    }
}
