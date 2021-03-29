package com.ndemaio.studentprojectspring.course;

import com.ndemaio.studentprojectspring.professor.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "course")
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private Professor professor;

}
