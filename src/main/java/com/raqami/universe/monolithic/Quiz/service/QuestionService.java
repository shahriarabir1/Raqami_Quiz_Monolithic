package com.raqami.universe.monolithic.Quiz.service;

import com.raqami.universe.monolithic.Quiz.Model.Question;
import com.raqami.universe.monolithic.Quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> findAll() throws Exception {
        try {
            return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findByCategory(String category) throws Exception {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> addAllQuestion(List<Question> question) {
        List<Question> questions=new ArrayList<>();
        for (Question q : question) {
            Question ques=questionRepository.save(q);
            questions.add(ques);
        }
        return questions;
    }
}
