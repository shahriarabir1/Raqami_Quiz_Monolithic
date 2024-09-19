package com.raqami.universe.monolithic.Quiz.repository;

import com.raqami.universe.monolithic.Quiz.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :num",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int num);
}
