package flpproject.akinator.controller;

import flpproject.akinator.model.Game;
import flpproject.akinator.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Game API", description = "Управление играми")
@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Создать игру")
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game saved = gameService.save(game);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить игру по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить игру по id")
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все игры")
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }
}
