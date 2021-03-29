package com.ndemaio.studentprojectspring.professor;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    private ProfessorService underTest;
    private ProfessorRepository professorRepository;

    @BeforeEach
    void setUp() {
        professorRepository = mock(ProfessorRepository.class);
        underTest = new ProfessorService(professorRepository);
    }

    @Test
    void itShouldSaveProfessor(){
        Professor professor = mock(Professor.class);
        when(professorRepository.save(professor)).thenReturn(professor);

        Professor savedProfessor = underTest.save(professor);

        verify(professorRepository).save(professor);
        assertThat(savedProfessor).isEqualTo(professor);
    }

    @Test
    void getProfessorByInexistentId_shouldThrowException(){
        Long inexistentId = 1l;
        when(professorRepository.findById(inexistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getProfessor(inexistentId)).isInstanceOf(EntityNotFoundException.class);
        verify(professorRepository).findById(inexistentId);
    }

    @Test
    void itShouldGetProfessor(){
        Long professorId = 1l;
        Professor professor = mock(Professor.class);
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));

        Professor obtainedProfessor = underTest.getProfessor(professorId);

        assertThat(obtainedProfessor).isEqualTo(professor);
        verify(professorRepository).findById(professorId);
    }

    @Test
    void updateAProfessorWithAInexistentId_shouldThrowException(){

        Long inexistentId = 1l;
        Professor professor = mock(Professor.class);
        when(professor.getId()).thenReturn(inexistentId);

        when(professorRepository.findById(inexistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.update(professor)).isInstanceOf(EntityNotFoundException.class);
        verify(professorRepository).findById(inexistentId);
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    void itShouldUpdateCorrectly(){
        Long professorId = 1l;

        Professor professor = mock(Professor.class);
        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));

        Professor professorToUpdate = new Professor();
        professorToUpdate.setActive(true);
        professorToUpdate.setId(professorId);

        underTest.update(professorToUpdate);

        verify(professorRepository).save(any(Professor.class));

    }
}