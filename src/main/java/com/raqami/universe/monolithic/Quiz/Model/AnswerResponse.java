package com.raqami.universe.monolithic.Quiz.Model;

import lombok.Data;

@Data
public class AnswerResponse {
    private Long questionId;
    private String answer;
}
