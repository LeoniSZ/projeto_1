package com.projeto_1.dto;

import com.projeto_1.domain.Aluno;


import lombok.Data;

@Data
public class AlunoDTO {
	
	private Long id;
	private String nome;
	private String matricula;
	private String cpf;
	private String curso;

	
	public AlunoDTO(Aluno c) {
		this.id = c.getId();
		this.matricula = c.getMatricula();
		this.nome = c.getNome();
		this.cpf = c.getCpf();
		this.curso = c.getCurso();
	}
}
