package com.ndemaio.studentprojectspring.student;

import com.ndemaio.studentprojectspring.mail.MailService;
import com.ndemaio.studentprojectspring.config.exceptions.EmailNotAvailableException;
import com.ndemaio.studentprojectspring.config.exceptions.StudentUnderAgeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentService underTest;
    private StudentRepository studentRepository;
    private MailService mailService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        mailService = mock(MailService.class);
        underTest = new StudentService(studentRepository, mailService);
    }

    @Test
    void itCantSignUpStudentUnderAge(){
        Student studentUnderAge = mock(Student.class);
        when(studentUnderAge.calculateAge()).thenReturn(15);

        assertThatThrownBy(() -> underTest.signUp(studentUnderAge)).isInstanceOf(StudentUnderAgeException.class);
        verifyNoInteractions(mailService);
    }

    @Test
    void itShouldSignUpValidStudent(){
        String studentEmail = "nicolasdemaio@mail.com";

        Student validStudent = mock(Student.class);
        when(validStudent.calculateAge()).thenReturn(20);
        when(validStudent.getEmail()).thenReturn(studentEmail);

        when(studentRepository.save(validStudent)).thenReturn(validStudent);

        Student studentEnrolled = underTest.signUp(validStudent);

        verify(studentRepository).save(validStudent);
        verify(mailService).sendMail(studentEmail, "You have been enrolled satisfactory.");
        assertThat(studentEnrolled).isEqualTo(validStudent);
    }

    @Test
    void itShouldGetStudentById(){
        Long studentId = 1l;
        Student student = mock(Student.class);
        when(student.getId()).thenReturn(studentId);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student obtainedStudent = underTest.getStudent(studentId);

        verify(studentRepository).findById(studentId);
        assertThat(obtainedStudent).isEqualTo(student);
    }

    @Test
    void getStudentByInexistentId_shouldThrowException(){
        Long inexistentId = 2l;

        when(studentRepository.findById(inexistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getStudent(inexistentId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Student not founded with ID ".concat(String.valueOf(inexistentId)));

        verify(studentRepository).findById(inexistentId);
    }

    @Test
    void itShouldGetAllStudents(){

        underTest.getStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void deleteStudentByInexistentId_shouldThrowException(){
        Long inexistentId = 1l;

        assertThatThrownBy(() -> underTest.deleteStudent(inexistentId)).isInstanceOf(EntityNotFoundException.class);

        verify(studentRepository, never()).deleteById(inexistentId);
    }

    @Test
    void itShouldDeleteStudentById(){

        Long studentId = 1l;
        Student student = mock(Student.class);
        when(student.getId()).thenReturn(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        underTest.deleteStudent(studentId);

        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void itCantSignUpStudentWithAlreadyRegisteredEmail(){

        String studentEmail = "test@mail.com";
        Student student = mock(Student.class);
        when(student.getEmail()).thenReturn(studentEmail);
        when(student.calculateAge()).thenReturn(20);

        when(studentRepository.existsByEmail(studentEmail)).thenReturn(true);

        assertThatThrownBy(() -> underTest.signUp(student)).isInstanceOf(EmailNotAvailableException.class);
        verify(studentRepository, never()).save(student);

    }

}