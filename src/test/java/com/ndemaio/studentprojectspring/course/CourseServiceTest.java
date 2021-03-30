package com.ndemaio.studentprojectspring.course;

import com.ndemaio.studentprojectspring.mail.MailService;
import com.ndemaio.studentprojectspring.student.Student;
import com.ndemaio.studentprojectspring.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    private CourseService underTest;
    private CourseRepository courseRepository;
    private StudentService studentService;
    private MailService mailService;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        studentService = mock(StudentService.class);
        mailService = mock(MailService.class);
        underTest = new CourseService(courseRepository, studentService, mailService);
    }

    @Test
    void itShouldGetAllCourses(){
        underTest.getAllCourses();

        verify(courseRepository).findAll();
    }

    @Test
    void itShouldGetCourseByExistentId(){
        Long courseId = 1l;
        Course course = mock(Course.class);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        Course courseObtained = underTest.getCourse(courseId);

        assertThat(courseObtained).isEqualTo(course);
        verify(courseRepository).findById(courseId);
    }

    @Test
    void itShouldThrowException_whenGetCourseByInexistentId(){

        Long inexistentId = 1l;
        when(courseRepository.findById(inexistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getCourse(inexistentId)).isInstanceOf(EntityNotFoundException.class);
        verify(courseRepository).findById(inexistentId);
    }

    @Test
    void itShouldSaveCourse(){
        Course course = mock(Course.class);
        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = underTest.save(course);

        verify(courseRepository).save(course);
        assertThat(savedCourse).isEqualTo(course);
    }

    @Test
    void itShouldDeleteCourse(){
        Long existentId = 1l;

        underTest.deleteCourse(existentId);

        verify(courseRepository).deleteById(existentId);
    }

    @Test
    void itShouldUpdatExistentCourse(){
        Long courseId = 1l;
        Course courseToUpdate = mock(Course.class);
        when(courseToUpdate.getId()).thenReturn(courseId);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseToUpdate));
        when(courseRepository.save(courseToUpdate)).thenReturn(courseToUpdate);

        Course courseUpdated = underTest.update(courseToUpdate);

        assertThat(courseUpdated).isEqualTo(courseToUpdate);
        verify(courseRepository).save(courseUpdated);
    }

    @Test
    void itShouldThrowException_whenUpdateACourseWithAInexistentId(){
        Long inexistentId = 1l;
        Course course = mock(Course.class);
        when(courseRepository.findById(inexistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(course)).isInstanceOf(EntityNotFoundException.class);
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void itShouldEnrollStudentInCourse(){
        Long studentId = 1l;
        Long courseId = 1l;
        EnrollRequest enrollRequest = new EnrollRequest(1l, 1l);
        Course course = mock(Course.class);
        Student student = mock(Student.class);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(studentService.getStudent(studentId)).thenReturn(student);

        EnrollRequest processedRequest = underTest.enroll(enrollRequest);

        assertThat(processedRequest).isEqualTo(enrollRequest);
        verify(course).addStudent(student);
        verify(courseRepository).save(course);
        verify(mailService).sendMail(student.getEmail(), "You have been enrolled in " + course.getSubjectName());
    }

    @Test
    void itShouldThrowException_whenStudentIdNotExist(){
        Long studentId = 1l;
        doThrow(EntityNotFoundException.class).when(studentService).getStudent(studentId);

        EnrollRequest enrollRequest = new EnrollRequest(studentId, 2l);

        assertThatThrownBy(() -> underTest.enroll(enrollRequest)).isInstanceOf(EntityNotFoundException.class);
        verifyNoInteractions(mailService);
        verify(courseRepository, never()).save(any(Course.class));
    }

}