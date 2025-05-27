package flpproject.akinator.controller;

import flpproject.akinator.model.Question;
import flpproject.akinator.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Question API", description = "Управление вопросами")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(summary = "Добавить вопрос")
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question saved = questionService.save(question);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Удалить вопрос по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить вопрос по id")
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить все вопросы")
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        return ResponseEntity.ok(questions);
    }
}
