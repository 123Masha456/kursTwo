package com.kurstwo.kursTwo;

import com.kurstwo.kursTwo.dto.Question;
import com.kurstwo.kursTwo.exceptions.QuestionNotFoundException;
import com.kurstwo.kursTwo.services.implementations.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    JavaQuestionService underTest = new JavaQuestionService();
    Question first = new Question("How many engines has Boeing 747?", "4");
    Question second = new Question("Do two plus two equals four", "Yes");

    @BeforeEach
    void setUp() {
        underTest = new JavaQuestionService();
    }

    @Test
    void addQuestion__questionAddedAndReturned() {
        var result = underTest.add(first.getQuestion(), first.getAnswer());
        assertTrue(underTest.getAll().contains(result));
        assertEquals(first, result);
    }

    @Test
    void removeQuestion_questionNotFound_thrownException() {
        QuestionNotFoundException ex =
                assertThrows(QuestionNotFoundException.class,
                        () -> underTest.remove(new Question(first.getQuestion(), first.getAnswer())));
        assertEquals("QUESTION NOT FOUND", ex.getMessage());
    }

    @Test
    void removeQuestion_questionFoundAndRemoved_returnQuestion() {
        underTest.add(first.getQuestion(), first.getAnswer());
        underTest.add(second.getQuestion(), second.getAnswer());
        var result = underTest.remove(new Question(second.getQuestion(), second.getAnswer()));
        assertEquals(second, result);
        assertFalse(underTest.getAll().contains(second));
    }

    @Test
    void getAllQuestions_allQuestionsFound_returnAllQuestions() {
        underTest.add(first.getQuestion(), first.getAnswer());
        underTest.add(second.getQuestion(), second.getAnswer());
        Collection<Question> result = underTest.getAll();
        assertEquals(List.of(first, second), result);
        assertFalse(underTest.getAll().isEmpty());
    }
    @Test
    void getRandomQuestion_listIsEmpty_thrownException(){
        QuestionNotFoundException ex =
                assertThrows(QuestionNotFoundException.class,
        () -> underTest.getRandomQuestion());
        assertEquals("QUESTION NOT FOUND", ex.getMessage());
    }

    @Test
    void getRandomQuestion__randomQuestionReturned() {
        underTest.add(first.getQuestion(), first.getAnswer());
        underTest.add(second.getQuestion(), second.getAnswer());
        var result = underTest.getRandomQuestion();
        assertTrue(underTest.getAll().contains(result));
    }
}