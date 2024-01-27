/**
 * Developed by S.A. WEERASINGHE - PS/2019/259
 */

package com.example.Quiz.quizModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class quiz {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String question;
    @NotBlank
    private String subjectCategory;

    @NotBlank
    private String grade;
    @NotBlank
    private String questionCategory;


    @ElementCollection
    private List<String> MultipleOptions;


    @ElementCollection
    private List<String> correctOption;
}