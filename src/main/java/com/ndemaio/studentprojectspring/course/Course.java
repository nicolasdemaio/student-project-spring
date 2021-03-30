package com.ndemaio.studentprojectspring.course;

import com.ndemaio.studentprojectspring.config.exceptions.NotAvailablePlacesException;
import com.ndemaio.studentprojectspring.config.exceptions.StudentIsAlreadyEnrolled;
import com.ndemaio.studentprojectspring.professor.Professor;
import com.ndemaio.studentprojectspring.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne
    private Professor professor;

    private int availablePlaces;

    @OneToMany
    private List<Student> students;

    public boolean hasEnrolledStudents() {
        return !students.isEmpty();
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public int getAvailablePlaces() {
        return this.availablePlaces;
    }

    public void addStudent(Student student) {

        if (availablePlaces <= 0) throw new NotAvailablePlacesException("Not available places to enroll a student.");
        if (isAlreadyEnrolled(student)) throw new StudentIsAlreadyEnrolled("Student is already enrolled.");

        students.add(student);
        availablePlaces--;
    }

    private boolean isAlreadyEnrolled(Student student) {
        return students.contains(student);
    }
}
