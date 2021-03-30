package com.ndemaio.studentprojectspring.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
}
