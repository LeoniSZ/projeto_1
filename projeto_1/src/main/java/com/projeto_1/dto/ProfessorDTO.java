package com.projeto_1.dto;

import com.projeto_1.domain.Professor;


import lombok.Data;

@Data
public class ProfessorDTO {
	
	private Long id;
	private String nome;
	private String matricula;
	private String curso;
	
	public ProfessorDTO(Professor c) {
		this.id = c.getId();
		this.matricula = c.getMatricula();
		this.nome = c.getNome();
		this.curso = c.getCurso();

	}
}