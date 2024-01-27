/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 */

package com.example.Quiz.QuizOperation;

import com.example.Quiz.quizModel.quiz;
import com.example.Quiz.QuizRepository.quizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class quizOperationClass implements quizOperationInterface{
    private final quizRepo quizRepo;
    @Override
    public quiz createQuiz(quiz q) {
        return quizRepo.save(q);
    }

    @Override
    public List<quiz> getAllQuestions() {
        return quizRepo.findAll();
    }

    @Override
    public Optional<quiz> getQuestionById(Long id) {
        return quizRepo.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {
        return quizRepo.findSpecifiedSubject();
    }

    @Override
    public List<String> getAllGrades() {
        return quizRepo.findSpecifiedGrade();
    }

    @Override
    public quiz updateQuiz(Long id, quiz question) throws ChangeSetPersister.NotFoundException {
        Optional<quiz> ques = this.getQuestionById(id);
        if (ques.isPresent()){
            quiz updatedQuiz = ques.get();
            updatedQuiz.setQuestion(question.getQuestion());
            updatedQuiz.setMultipleOptions(question.getMultipleOptions());
            updatedQuiz.setCorrectOption(question.getCorrectOption());
            return quizRepo.save(updatedQuiz);
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }
    @Override
    public void deleteQuestion(Long id) {
        quizRepo.deleteById(id);
    }
    @Override
    public List<quiz> getQuizForStudent(Integer numOfQuestions, String subject) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return quizRepo.findBySubject(subject, pageable).getContent();
    }

    @Override
    public List<quiz> getQuizForStudentByGrade(Integer numOfQuestions, String grade) {
        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return quizRepo.findByGrade(grade, pageable).getContent();
    }
}