package com.ndemaio.studentprojectspring.student;

import com.ndemaio.studentprojectspring.mail.MailService;
import com.ndemaio.studentprojectspring.config.exceptions.EmailNotAvailableException;
import com.ndemaio.studentprojectspring.config.exceptions.StudentUnderAgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public static final int ALLOWED_AGE = 18;
    public static final String STUDENT_UNDER_AGE_MSG = "Not is possible to enroll a student under age.";

    private StudentRepository studentRepository;
    private MailService mailService;

    @Autowired
    public StudentService(StudentRepository studentRepository, MailService mailService){
        this.studentRepository = studentRepository;
        this.mailService = mailService;
    }

    public Student signUp(Student student) {

        String studentEmail = student.getEmail();

        if (isStudentUnderAge(student)){
            throw new StudentUnderAgeException(STUDENT_UNDER_AGE_MSG);
        }

        if (studentRepository.existsByEmail(studentEmail)) {
            throw new EmailNotAvailableException("Indicated email is already in use.");
        }

        mailService.sendMail(studentEmail, "You have been enrolled satisfactory.");
        return studentRepository.save(student);
    }

    public Student getStudent(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isEmpty()){
            throw new EntityNotFoundException("Student not founded with ID ".concat(String.valueOf(studentId)));
        }

        return optionalStudent.get();
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long studentId) {
        Student studentToDelete = this.getStudent(studentId);

        studentRepository.deleteById(studentToDelete.getId());
    }

    private boolean isStudentUnderAge(Student student) {
        return student.calculateAge() < ALLOWED_AGE;
    }


}
