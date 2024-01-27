/**
 * Developed by S.A.WEERASINGHE - PS/2019/259
 */


package com.example.Quiz.QuizRepository;
import com.example.Quiz.quizModel.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.net.ContentHandler;
import java.util.List;


public interface quizRepo extends JpaRepository<quiz, Long> {

    @Query("SELECT DISTINCT q.subject FROM Question q")

    List<String> findSpecifiedSubject();
    Page<quiz> findBySubject(String subject, Pageable pageable);

    List<String> findSpecifiedGrade();
    Page<quiz> findByGrade(String grade, Pageable pageable);
}