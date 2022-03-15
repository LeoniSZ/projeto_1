package com.projeto_1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "projeto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getPalavraChave1() {
		return palavraChave1;
	}

	public void setPalavraChave1(String palavraChave1) {
		this.palavraChave1 = palavraChave1;
	}

	public String getPalavraChave2() {
		return palavraChave2;
	}

	public void setPalavraChave2(String palavraChave2) {
		this.palavraChave2 = palavraChave2;
	}

	public String getPalavraChave3() {
		return palavraChave3;
	}

	public void setPalavraChave3(String palavraChave3) {
		this.palavraChave3 = palavraChave3;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Professor getProfessorResponsavel() {
		return professorResponsavel;
	}

	public void setProfessorResponsavel(Professor professorResponsavel) {
		this.professorResponsavel = professorResponsavel;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "resumo")
	private String resumo;
	
	@Column(name = "palavraChave1")
	private String palavraChave1;
	
	@Column(name = "palavraChave2")
	private String palavraChave2;
	
	@Column(name = "palavraChave3")
	private String palavraChave3;
	
	@Column(name = "urlDocumento")
	private String url;
	
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fk_professor_id", foreignKey = @ForeignKey(name = "fk_professor"), referencedColumnName = "id")
	private Professor professorResponsavel;
	
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "projeto_alunos", 
    			joinColumns={@JoinColumn(name="fK_projeto_id", foreignKey = @ForeignKey(name = "fk_projeto"), referencedColumnName = "id")},
    			inverseJoinColumns={@JoinColumn(name="fk_aluno_id", foreignKey = @ForeignKey(name = "fk_aluno"), referencedColumnName = "id")}    				)
	private List<Aluno> alunos;

}
