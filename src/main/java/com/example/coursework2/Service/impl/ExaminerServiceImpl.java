package com.example.coursework2.Service.impl;

import com.example.coursework2.Service.ExaminerService;
import com.example.coursework2.Service.QuestionService;
import com.example.coursework2.exception.IncorrectAmountOfQuestions;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> qetQuestions(int amount) {
        if(amount > questionService.getAll().size() || amount <= 0){
            throw new IncorrectAmountOfQuestions();
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount){
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }

}
