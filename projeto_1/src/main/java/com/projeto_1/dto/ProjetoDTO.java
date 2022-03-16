package com.projeto_1.dto;

import java.util.List;

import com.projeto_1.domain.Aluno;
import com.projeto_1.domain.Professor;
import com.projeto_1.domain.Projeto;

import lombok.Data;

@Data
public class ProjetoDTO {
	
	private Long id;
	private String titulo;
	private String area;
	private String resumo;
	private String palavraChave1;
	private String palavraChave2;
	private String palavraChave3;
	private String url;
	private Professor professorResponsavel;
	private List<Aluno> aluno;
	
	public ProjetoDTO(Projeto c) {
		this.id = c.getId();
		this.titulo = c.getTitulo();
		this.area = c.getArea();
		this.resumo = c.getResumo();
		this.palavraChave1 = c.getPalavraChave1();
		this.palavraChave2 = c.getPalavraChave2();
		this.palavraChave3 = c.getPalavraChave3();
		this.url = c.getUrl();
		this.professorResponsavel = c.getProfessorResponsavel();
		this.aluno = c.getAlunos();
	}
}