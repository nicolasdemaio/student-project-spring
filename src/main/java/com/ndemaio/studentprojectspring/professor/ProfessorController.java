package com.ndemaio.studentprojectspring.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/professors")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody Professor professor){

        Professor resultantProfessor = professorService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultantProfessor);
    }

    @GetMapping (path = "/{professorId}")
    public ResponseEntity<Professor> getProfessor(@PathVariable Long professorId){

        Professor obtainedProfessor = professorService.getProfessor(professorId);
        return ResponseEntity.status(HttpStatus.OK).body(obtainedProfessor);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors(){

        List<Professor> professors = professorService.getProfessors();
        return ResponseEntity.status(HttpStatus.OK).body(professors);
    }

    @DeleteMapping (path = "/{professorId}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long professorId){

        professorService.deleteProfessor(professorId);
        return ResponseEntity.status(HttpStatus.OK).body("Professor was deleted correctly.");
    }

    @PutMapping
    public ResponseEntity<Professor> update(@RequestBody Professor professor){

        Professor updatedProfessor = professorService.update(professor);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProfessor);
    }

}
