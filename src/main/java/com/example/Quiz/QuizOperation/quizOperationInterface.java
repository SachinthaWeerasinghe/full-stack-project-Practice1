/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 */

package com.example.Quiz.QuizOperation;

import com.example.Quiz.quizModel.quiz;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;



public interface quizOperationInterface {

    /** To create a new quiz **/
    quiz createQuiz(quiz q);

    /** To get all the existing questions currently available **/
    List<quiz> getAllQuestions();

    /** To get the available questions based on their id */
    Optional<quiz> getQuestionById(Long id);

    /** To get all the available subjects */
    List<String> getAllSubjects();

    /** To get all the grades that quizzes are available **/
    List<String> getAllGrades();

    /** To provide the accessibility for updating the quizzes */
    quiz updateQuiz(Long id, quiz q) throws ChangeSetPersister.NotFoundException;

    /** To delete the existing quizzes */
    void  deleteQuestion(Long id);

    List<quiz> getQuizForStudent(Integer numOfQuestions, String subject);

    List<quiz> getQuizForStudentByGrade(Integer numOfQuestions, String grade);


}