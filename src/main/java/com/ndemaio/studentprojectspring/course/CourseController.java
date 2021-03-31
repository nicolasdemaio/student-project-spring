package com.ndemaio.studentprojectspring.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){

        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping (path = "/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId){

        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course){

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @DeleteMapping (path = "/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){

        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("Course was deleted correctly.");
    }

    @PutMapping
    public ResponseEntity<Course> update(@RequestBody Course course){

        return ResponseEntity.status(HttpStatus.OK).body(courseService.update(course));
    }

    @PostMapping (path = "{courseId}/enrollments")
    public ResponseEntity<EnrollRequest> enroll(@PathVariable Long courseId, @RequestParam(value = "studentId") Long studentId){

        EnrollRequest enrollRequest = new EnrollRequest(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.enroll(enrollRequest));
    }

}
