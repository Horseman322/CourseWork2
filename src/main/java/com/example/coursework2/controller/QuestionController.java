package com.example.coursework2.controller;

import com.example.coursework2.Service.QuestionService;
import com.example.coursework2.model.Question;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer){
        return questionService.add(question, answer);
    }

    @DeleteMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer){
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping("/remove")
    public Collection<Question> getQuestion(){
        return questionService.getAll();
    }

}
