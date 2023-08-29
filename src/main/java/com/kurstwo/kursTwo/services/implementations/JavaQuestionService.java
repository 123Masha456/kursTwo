package com.kurstwo.kursTwo.services.implementations;

import com.kurstwo.kursTwo.dto.Question;
import com.kurstwo.kursTwo.exceptions.QuestionNotFoundException;
import com.kurstwo.kursTwo.services.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final List<Question> questionList;
    private final Random random;

    public JavaQuestionService() {
        this.questionList = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        var questionEntity = new Question(question, answer);
        add(questionEntity);
        return questionEntity;
    }

    @Override
    public Question add(Question question) {
        questionList.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionList.contains(question)) {
            throw new QuestionNotFoundException("QUESTION NOT FOUND");
        }
        questionList.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionList;
    }

    @Override
    public Question getRandomQuestion() {
        return questionList.get(random.nextInt(questionList.size()));
    }
}
