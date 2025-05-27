package flpproject.akinator.controller;

import flpproject.akinator.dto.AliasGameDTO;
import flpproject.akinator.model.AliasGame;
import flpproject.akinator.model.Session;
import flpproject.akinator.model.User;
import flpproject.akinator.service.AliasGameService;
import flpproject.akinator.service.SessionService;
import flpproject.akinator.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AliasGame API", description = "Управление отгаданными играми")
@RestController
@RequestMapping("/api/alias-games")
public class AliasGameController {

    private final AliasGameService aliasGameService;
    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public AliasGameController(AliasGameService aliasGameService,SessionService sessionService,UserService userService) {
        this.aliasGameService = aliasGameService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Operation(summary = "Добавить отгаданную игру")
    @PostMapping
    public ResponseEntity<AliasGame> createAliasGame(@RequestBody AliasGameDTO request) {
        User user = userService.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        // 2. Найти сессию по sessionId
        Session session = sessionService.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + request.getSessionId()));

        // 3. Создать объект AliasGame
        AliasGame aliasGame = new AliasGame();
        aliasGame.setUser(user);
        aliasGame.setSession(session);
        aliasGame.setAnswers(request.getAnswers());
        aliasGame.setGameName(request.getGameName());

        // 4. Сохранить
        AliasGame saved = aliasGameService.save(aliasGame);

        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить отгаданную игру по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAliasGame(@PathVariable Long id) {
        aliasGameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить отгаданную игру по id")
    @GetMapping("/{id}")
    public ResponseEntity<AliasGame> getAliasGameById(@PathVariable Long id) {
        return aliasGameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все отгаданные игры")
    @GetMapping
    public ResponseEntity<List<AliasGame>> getAllAliasGames() {
        List<AliasGame> aliasGames = aliasGameService.findAll();
        return ResponseEntity.ok(aliasGames);
    }

    @Operation(summary = "Получить ответ по юзеру и сессии")
    @GetMapping("/session/{sessionId}/user/{userId}")
    public List<AliasGame> getBySessionAndUser(@PathVariable Long sessionId, @PathVariable Long userId) {
        return aliasGameService.getBySessionAndUser(sessionId, userId);
    }

    @Operation(summary = "Получить ответ по юзеру")
    @GetMapping("/user/{userId}")
    public List<AliasGame> getByUserId(@PathVariable Long userId) {
        return aliasGameService.getByUserId(userId);
    }
}
