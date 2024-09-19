package com.raqami.universe.monolithic.Quiz.controller;

import com.raqami.universe.monolithic.Quiz.Model.Question;
import com.raqami.universe.monolithic.Quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("getAll")
    public ResponseEntity<List<Question>> getAllQuestions() throws Exception {
            return questionService.findAll();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) throws Exception {

            return  questionService.findByCategory(category);


    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok().body(questionService.addQuestion(question));
    }
    @PostMapping("add/all")
    public ResponseEntity<List<Question>> addAllQuestion(@RequestBody List<Question> question) {
        return ResponseEntity.ok().body(questionService.addAllQuestion(question));
    }

}
