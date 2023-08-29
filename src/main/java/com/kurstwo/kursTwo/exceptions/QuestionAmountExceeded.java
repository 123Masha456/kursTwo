package com.kurstwo.kursTwo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "THERE ARE NO MORE QUESTIONS")
public class QuestionAmountExceeded extends RuntimeException {
    public QuestionAmountExceeded(String message) {
        super(message);
    }
}
