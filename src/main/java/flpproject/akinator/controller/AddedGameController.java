package flpproject.akinator.controller;


import flpproject.akinator.dto.AddedGameDTO;
import flpproject.akinator.model.AddedGame;
import flpproject.akinator.model.Session;
import flpproject.akinator.service.AddedGameService;
import flpproject.akinator.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "AddedGame API", description = "Управление добавленными играми")
@RestController
@RequestMapping("/api/added-games")
public class AddedGameController {
    private final AddedGameService addedGameService;
    private final SessionService sessionService;

    @Autowired
    public AddedGameController(AddedGameService addedGameService, SessionService sessionService) {
        this.addedGameService = addedGameService;
        this.sessionService = sessionService;
    }

    @Operation(summary = "Добавить новую игру")
    @PostMapping
    public ResponseEntity<AddedGame> createAddedGame(@RequestBody AddedGameDTO request) {
        Session session = sessionService.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        AddedGame addedGame = new AddedGame();
        addedGame.setName(request.getText());
        addedGame.setAddedByUser(session.getUser());
        addedGame.setAddedAt(LocalDateTime.now());
        AddedGame saved = addedGameService.save(addedGame);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить добавленную игру по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddedGame(@PathVariable Long id) {
        addedGameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить добавленную игру по id")
    @GetMapping("/{id}")
    public ResponseEntity<AddedGame> getAddedGameById(@PathVariable Long id) {
        return addedGameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все добавленные игры")
    @GetMapping
    public ResponseEntity<List<AddedGame>> getAllAddedGames() {
        List<AddedGame> addedGames = addedGameService.findAll();
        return ResponseEntity.ok(addedGames);
    }

    @Operation(summary = "Получить все добавленные игры пл юзеру")
    @GetMapping("/user/{userId}")
    public List<AddedGame> getAddedGamesByUser(@PathVariable Long userId) {
        return addedGameService.getAddedGamesByUserId(userId);
    }
}
