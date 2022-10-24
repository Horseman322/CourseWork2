package com.example.coursework2.Service;

import com.example.coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> qetQuestions(int amount);

}
