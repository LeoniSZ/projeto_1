package com.projeto_1.repository;

import java.util.List;

import com.projeto_1.domain.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	List<Professor> findByMatricula(String matricula);
	List<Professor> findByCurso(String curso);
}
