package flpproject.akinator.controller;

import flpproject.akinator.dto.SessionDTO;
import flpproject.akinator.model.Session;
import flpproject.akinator.model.User;
import flpproject.akinator.service.SessionService;
import flpproject.akinator.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Session API", description = "Управление сеансами игры")
@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Operation(summary = "Создать сеанс")
    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody SessionDTO request) {
        User user = userService.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        // 2. Создать объект Session
        Session session = new Session();
        session.setUser(user);
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());

        // 3. Сохранить сессию
        Session saved = sessionService.save(session);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить сеанс по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить сеанс по id")
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        return sessionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все сеансы")
    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.findAll();
        return ResponseEntity.ok(sessions);
    }

    @Operation(summary = "Получить все сеансы по юзеру")
    @GetMapping("/user/{userId}")
    public List<Session> getSessionsByUser(@PathVariable Long userId) {
        return sessionService.getSessionsByUserId(userId);
    }
}
