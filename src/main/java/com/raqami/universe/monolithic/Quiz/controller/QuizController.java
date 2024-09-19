package com.raqami.universe.monolithic.Quiz.controller;

import com.raqami.universe.monolithic.Quiz.Model.AnswerResponse;
import com.raqami.universe.monolithic.Quiz.Model.Question;
import com.raqami.universe.monolithic.Quiz.Model.QuestionWrapper;
import com.raqami.universe.monolithic.Quiz.Model.Quiz;
import com.raqami.universe.monolithic.Quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int num,@RequestParam String title) {
    return quizService.createQuiz(category,num,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuiz(@PathVariable Long id) {
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id,@RequestBody List<AnswerResponse>answer) {
        return quizService.calculateResult(id,answer);
    }
}
