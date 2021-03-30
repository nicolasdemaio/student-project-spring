package com.ndemaio.studentprojectspring.course;

import com.ndemaio.studentprojectspring.mail.MailService;
import com.ndemaio.studentprojectspring.student.Student;
import com.ndemaio.studentprojectspring.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private StudentService studentService;
    private MailService mailService;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentService studentService, MailService mailService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.mailService = mailService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                                        .orElseThrow(() -> new EntityNotFoundException("Not exist course with this ID."));
        return course;
    }


    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long existentId) {
        courseRepository.deleteById(existentId);
    }

    public Course update(Course course){

        Course courseToUpdate = this.getCourse(course.getId());

        courseToUpdate.setAvailablePlaces(course.getAvailablePlaces());
        courseToUpdate.setProfessor(course.getProfessor());
        courseToUpdate.setStudents(course.getStudents());
        courseToUpdate.setSubjectName(course.getSubjectName());

        return courseRepository.save(courseToUpdate);
    }

    public EnrollRequest enroll(EnrollRequest enrollRequest){
        Student student = studentService.getStudent(enrollRequest.getStudentId());
        Course course = this.getCourse(enrollRequest.getCourseId());

        course.addStudent(student);
        mailService.sendMail(student.getEmail(), "You have been enrolled in " + course.getSubjectName());
        courseRepository.save(course);
        return enrollRequest;
    }
}
