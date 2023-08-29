package com.kurstwo.kursTwo.services.interfaces;

import com.kurstwo.kursTwo.dto.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
