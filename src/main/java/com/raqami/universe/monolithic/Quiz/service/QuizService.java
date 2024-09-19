package com.raqami.universe.monolithic.Quiz.service;

import com.raqami.universe.monolithic.Quiz.Model.AnswerResponse;
import com.raqami.universe.monolithic.Quiz.Model.Question;
import com.raqami.universe.monolithic.Quiz.Model.QuestionWrapper;
import com.raqami.universe.monolithic.Quiz.Model.Quiz;
import com.raqami.universe.monolithic.Quiz.repository.QuestionRepository;
import com.raqami.universe.monolithic.Quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {
        List<Question>questions=questionRepository.findRandomQuestionByCategory(category,num);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return ResponseEntity.ok("Quiz created");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Long id) {
       Optional<Quiz> quiz= quizRepository.findById(id);
       List<Question>questions=quiz.get().getQuestions();
       List<QuestionWrapper> questionWrappers=new ArrayList<>();
       for(Question question:questions){
           QuestionWrapper questionWrapper=new QuestionWrapper(
                   question.getId(),
                   question.getQuestion(),
                   question.getOption1(),
                   question.getOption2(),
                   question.getOption3(),
                   question.getOption4());
           questionWrappers.add(questionWrapper);

       }
        return ResponseEntity.ok(questionWrappers);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<AnswerResponse> answer) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        List<Question>questions=quiz.get().getQuestions();
        int result=0;
        int i=0;
        for(AnswerResponse ans:answer){
            if(ans.getAnswer().equals(questions.get(i).getAnswer())){
                result++;
            }
            i++;
        }
        return ResponseEntity.ok(result);
    }
}
