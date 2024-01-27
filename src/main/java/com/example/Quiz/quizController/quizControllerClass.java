/**
 * Developed By S.A. WEERASINGHE - PS/2019/259
 */

package com.example.Quiz.quizController;

import com.example.Quiz.quizModel.quiz;
import com.example.Quiz.QuizOperation.quizOperationInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor


/**
 * S. A Weerasinghe- change the class again
 */
public class quizControllerClass {
    private final quizOperationInterface quizOperation;

    /** To Create a new question for a quiz **/
    @PostMapping("/create-new-quiz")
    public ResponseEntity<quiz> createQuiz(@Valid @RequestBody quiz question){
        quiz createdQuiz = quizOperation.createQuiz(question);
        return ResponseEntity.status(CREATED).body(createdQuiz);
    }


    /** To get all the questions available **/
    @GetMapping("/all-questions")
    public ResponseEntity<List<quiz>> getAllQuestions(){
        List<quiz> questions = quizOperation.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    /** To get a question by specifying its id **/
    @GetMapping("/question/{id}")
    public ResponseEntity<quiz> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Optional<quiz> ques = quizOperation.getQuestionById(id);
        if (ques.isPresent()){
            return ResponseEntity.ok(ques.get());
        }else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    /** To update an existing quiz **/
    @PutMapping("/question/{id}/update")
    public ResponseEntity<quiz> updateQuiz(
            @PathVariable Long id, @RequestBody quiz question) throws ChangeSetPersister.NotFoundException {
        quiz updatedQuiz = quizOperation.updateQuiz(id, question);
        return ResponseEntity.ok(updatedQuiz);
    }

    /** To Delete an existing quiz question **/
    @DeleteMapping("/question/{id}/delete")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        quizOperation.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    /**
        To get all the subjects
     **/
    @GetMapping("/subjects")
    public ResponseEntity<List<String>> getAllSubjects(){
        List<String> subjects = quizOperation.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    /**
     To get an quiz for the student
     **/
    @GetMapping("/quiz/fetch-quiz-for-student")
    public ResponseEntity<List<quiz>> getQuizForStudent(
            @RequestParam Integer numOfQuestions, @RequestParam String subject){
        List<quiz> allQuestions = quizOperation.getQuizForStudent(numOfQuestions, subject);

        List<quiz> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);        /** To Shuffle the questions **/

        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());

        /** To available questions become randomized when retaking the quiz **/
        List<quiz> randomQues = mutableQuestions.subList(0, availableQuestions);
        return ResponseEntity.ok(randomQues);
    }

    /**
        To get a quiz for the student according to their grade
     **/
    @GetMapping("/quiz/fetch-quiz-for-user-by-grade")
    public ResponseEntity<List<quiz>> getQuizForStudentByGrade(
            @RequestParam Integer numOfQuestions, @RequestParam String grade){
        List<quiz> allQuestions = quizOperation.getQuizForStudent(numOfQuestions, grade);

        List<quiz> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);

        /** Calculate the number of available questions based on
          the minimum of numberOfQuestion and the size of the mutableQuestions
         */
        int availableQuestions = Math.min(numOfQuestions, mutableQuestions.size());

        /** To available questions become randomized when retaking the quiz **/
        List<quiz> randomQues = mutableQuestions.subList(0, availableQuestions);
        return ResponseEntity.ok(randomQues);
    }

}