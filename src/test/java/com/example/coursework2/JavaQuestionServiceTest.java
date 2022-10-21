package com.example.coursework2;

import com.example.coursework2.Service.QuestionService;
import com.example.coursework2.Service.impl.JavaQuestionService;
import com.example.coursework2.exception.QuestionAlreadyExistsException;
import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    public void afterEach(){
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addTest(){
        assertThat(questionService.getAll()).isEmpty();
        Question expected = add(new Question("q1", "a1" ));
        questionService.add("q1", "a1");
        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected, new Question("q2", "a2"));
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected));
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("q2", "a2"));
    }

    @Test
    public void removeTest(){
        assertThat(questionService.getAll().isEmpty());
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() ->questionService.remove(new Question("test", "test")));
        Question expected = add(new Question("q1", "a1" ));
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() ->questionService.remove(new Question("test", "test")));
        questionService.remove(expected);
        assertThat(questionService.getAll()).isEmpty();
    }
    @Test
    public void qetRandomQuestionTest(){
        for (int i = 1; i <= 5; i++) {
            add(new Question("q" + i, "a" + i));
        }
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    private Question add(Question question){
        int sizeBefore = questionService.getAll().size();
        questionService.add(question);
        assertThat(questionService.getAll()).hasSize(sizeBefore + 1);
        assertThat(questionService.getAll()).contains(question);
        return question;
    }

}
