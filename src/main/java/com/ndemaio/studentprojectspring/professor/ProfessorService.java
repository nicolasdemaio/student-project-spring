package com.ndemaio.studentprojectspring.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor save(Professor professor){
        return professorRepository.save(professor);
    }

    public Professor getProfessor(Long professorId){

        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);

        if (optionalProfessor.isEmpty()) throw new EntityNotFoundException("Not exist professor with ID " + String.valueOf(professorId));

        return optionalProfessor.get();
    }

    public List<Professor> getProfessors(){
        return professorRepository.findAll();
    }

    public void deleteProfessor(Long professorId){

        Professor professor = getProfessor(professorId);
        professorRepository.delete(professor);
    }

    public Professor update(Professor professor){
        Professor professorToUpdate = getProfessor(professor.getId());

        professorToUpdate.setActive(professor.isActive());
        professorToUpdate.setBirthDate(professor.getBirthDate());
        professorToUpdate.setFirstName(professor.getFirstName());
        professorToUpdate.setLastName(professor.getLastName());

        return professorRepository.save(professorToUpdate);
    }
}
