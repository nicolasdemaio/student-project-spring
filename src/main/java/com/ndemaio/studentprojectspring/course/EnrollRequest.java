package com.ndemaio.studentprojectspring.course;

public class EnrollRequest {

    private Long studentId;
    private Long courseId;

    public EnrollRequest(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }
}
