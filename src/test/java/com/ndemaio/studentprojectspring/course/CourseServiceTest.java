package com.ndemaio.studentprojectspring.course;

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

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        underTest = new CourseService(courseRepository);
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
}