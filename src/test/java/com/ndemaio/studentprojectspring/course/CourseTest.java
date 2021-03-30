package com.ndemaio.studentprojectspring.course;

import com.ndemaio.studentprojectspring.config.exceptions.NotAvailablePlacesException;
import com.ndemaio.studentprojectspring.config.exceptions.StudentIsAlreadyEnrolled;
import com.ndemaio.studentprojectspring.professor.Professor;
import com.ndemaio.studentprojectspring.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseTest {

    private Course underTest;

    @BeforeEach
    void setUp() {
        underTest = new Course(1l, "Science", new Professor(), 30, new ArrayList<Student>());
    }

    @Test
    void whenIsCreated_DoesNotHaveEnrolledStudents(){
        assertThat(underTest.hasEnrolledStudents()).isFalse();
        assertThat(underTest.getAvailablePlaces()).isEqualTo(30);
    }

    @Test
    void itShouldAddStudent(){
        Student student = mock(Student.class);

        underTest.addStudent(student);

        assertThat(underTest.hasEnrolledStudents()).isTrue();
        assertThat(underTest.getAvailablePlaces()).isEqualTo(29);
    }

    @Test
    void itShouldNotAddSameStudentTwice(){
        Student student = mock(Student.class);

        underTest.addStudent(student);

        assertThatThrownBy(() -> underTest.addStudent(student)).isInstanceOf(StudentIsAlreadyEnrolled.class);
        assertThat(underTest.getAvailablePlaces()).isEqualTo(29);
    }

    @Test
    void itCannotEnrollStudentWithoutAvailablePlaces(){
        Student student = mock(Student.class);
        underTest.setAvailablePlaces(0);

        assertThatThrownBy(() -> underTest.addStudent(student)).isInstanceOf(NotAvailablePlacesException.class);
        assertThat(underTest.getAvailablePlaces()).isEqualTo(0);
    }
}