package com.kurstwo.kursTwo;

import com.kurstwo.kursTwo.dto.Question;
import com.kurstwo.kursTwo.exceptions.QuestionAmountExceeded;
import com.kurstwo.kursTwo.services.implementations.ExaminerServiceImpl;
import com.kurstwo.kursTwo.services.interfaces.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    QuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl underTest;
    Question a = new Question("Is sky green", "No");
    Question b = new Question("Is grass blue", "No");
    Question c = new Question("Is ball round", "Yes");
    Collection<Question> questions;

    @BeforeEach
    void beforeEach() {
        questions = List.of(a, b, c);
    }

    @Test
    void getQuestions_amountIsActual_returnSetOfQuestions() {
        int amount = 3;
        when(questionService.getRandomQuestion()).thenReturn(a, b, c, a, c, b);
        Collection<Question> result = underTest.getQuestions(amount);
        assertEquals(amount, result.size());
        assertEquals(amount, result.stream().distinct().count());
    }

    @Test
    void getQuestions_amountIsExceeded_thrownException() {
        int amount = 10;
        when(questionService.getAll()).thenReturn(questions);
        QuestionAmountExceeded ex =
                assertThrows(QuestionAmountExceeded.class,
                        () -> underTest.getQuestions(amount));
        assertEquals("THERE ARE NO MORE QUESTIONS", ex.getMessage());
    }
}
