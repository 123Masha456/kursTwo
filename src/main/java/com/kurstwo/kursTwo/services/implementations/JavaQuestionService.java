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
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {
        questionList.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionList.remove(question)) {
            throw new QuestionNotFoundException("QUESTION NOT FOUND");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionList;
    }

    @Override
    public Question getRandomQuestion() {
        if(questionList.isEmpty()){
            throw new QuestionNotFoundException("QUESTION NOT FOUND");
        }
        return questionList.get(random.nextInt(questionList.size()));
    }
}
