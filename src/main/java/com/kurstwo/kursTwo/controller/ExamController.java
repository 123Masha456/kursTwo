package com.kurstwo.kursTwo.controller;

import com.kurstwo.kursTwo.dto.Question;
import com.kurstwo.kursTwo.services.interfaces.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    public final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Question>> getQuestions(@RequestParam int amount) {
        return new ResponseEntity<>(examinerService.getQuestions(3), HttpStatus.OK);
    }
}
