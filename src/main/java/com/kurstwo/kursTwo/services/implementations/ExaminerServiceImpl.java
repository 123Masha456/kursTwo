package com.kurstwo.kursTwo.services.implementations;

import com.kurstwo.kursTwo.dto.Question;
import com.kurstwo.kursTwo.exceptions.QuestionAmountExceeded;
import com.kurstwo.kursTwo.services.interfaces.ExaminerService;
import com.kurstwo.kursTwo.services.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        if (questionService.getAll().stream().distinct().count() > amount) {
            throw new QuestionAmountExceeded("THERE ARE NO MORE QUESTIONS");
        }
        return questions;
    }
}
