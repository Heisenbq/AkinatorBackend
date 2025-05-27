package flpproject.akinator.service;

import flpproject.akinator.model.Session;
import flpproject.akinator.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Optional<Session> findById(Long id) {
        return sessionRepository.findById(id);
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public List<Session> getSessionsByUserId(Long userId) {
        return sessionRepository.findByUser_UserId(userId);
    }

    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
}
