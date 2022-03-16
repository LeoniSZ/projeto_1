package com.projeto_1.repository;

import java.util.List;

import com.projeto_1.domain.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	List<Aluno> findByMatricula(String matricula);
	List<Aluno> findByCurso(String curso);
}
