package com.raqami.universe.monolithic.Quiz.repository;

import com.raqami.universe.monolithic.Quiz.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
