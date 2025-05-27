package flpproject.akinator.controller;

import flpproject.akinator.dto.AddedQuestionDTO;
import flpproject.akinator.model.AddedQuestion;
import flpproject.akinator.model.Session;
import flpproject.akinator.service.AddedQuestionService;
import flpproject.akinator.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "AddedQuestion API", description = "Управление добавленными вопросами")
@RestController
@RequestMapping("/api/added-questions")
public class AddedQuestionController {

    private final AddedQuestionService addedQuestionService;
    private final SessionService sessionService;

    @Autowired
    public AddedQuestionController(AddedQuestionService addedQuestionService, SessionService sessionService) {
        this.addedQuestionService = addedQuestionService;
        this.sessionService = sessionService;
    }

    @Operation(summary = "Добавить новый вопрос")
    @PostMapping
    public ResponseEntity<AddedQuestion> createAddedQuestion(@RequestBody AddedQuestionDTO request) {
        // Найти сессию по id
        Session session = sessionService.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Создать новый AddedQuestion
        AddedQuestion addedQuestion = new AddedQuestion();
        addedQuestion.setText(request.getText());
        // Связать AddedQuestion с пользователем, который создал сессию
        addedQuestion.setAddedByUser(session.getUser());
        addedQuestion.setAddedAt(LocalDateTime.now());

        // Сохранить
        AddedQuestion saved = addedQuestionService.save(addedQuestion);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить добавленный вопрос по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddedQuestion(@PathVariable Long id) {
        addedQuestionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить добавленный вопрос по id")
    @GetMapping("/{id}")
    public ResponseEntity<AddedQuestion> getAddedQuestionById(@PathVariable Long id) {
        return addedQuestionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все добавленные вопросы")
    @GetMapping
    public ResponseEntity<List<AddedQuestion>> getAllAddedQuestions() {
        List<AddedQuestion> addedQuestions = addedQuestionService.findAll();
        return ResponseEntity.ok(addedQuestions);
    }

    @Operation(summary = "Получить все добавленные вопросы по юзеру")
    @GetMapping("/user/{userId}")
    public List<AddedQuestion> getAddedQuestionsByUser(@PathVariable Long userId) {
        return addedQuestionService.getAddedQuestionsByUserId(userId);
    }

}
