package com.projeto_1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.projeto_1.domain.Aluno;
import com.projeto_1.domain.Professor;
import com.projeto_1.domain.Projeto;
import com.projeto_1.dto.ProjetoDTO;
import com.projeto_1.repository.ProjetoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	public List<ProjetoDTO> getProjetos(){
		return projetoRepository.findAll().stream().map(ProjetoDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Projeto> getProjetoById(Long id) {
		return projetoRepository.findById(id);
	}
	
	public List<ProjetoDTO> getProjetosByArea(String area) {
		return projetoRepository.findByArea(area).stream().map(ProjetoDTO::new).collect(Collectors.toList());
	}
	
	public Projeto cadastrar(Projeto projeto) {
		return projetoRepository.save(projeto);
	}
	
	public Projeto atualizar(Projeto projeto, Long id) {
		
		Optional<Projeto> optional = getProjetoById(id);
		if (optional.isPresent()) {
			Projeto projetoBD = optional.get();
			projetoBD.setTitulo(projeto.getTitulo());
			projetoBD.setArea(projeto.getArea());
			projetoBD.setResumo(projeto.getResumo());
			projetoBD.setPalavraChave1(projeto.getPalavraChave1());
			projetoBD.setPalavraChave2(projeto.getPalavraChave2());
			projetoBD.setPalavraChave3(projeto.getPalavraChave3());
			projetoBD.setUrl(projeto.getUrl());
			projetoBD.setProfessorResponsavel(projeto.getProfessorResponsavel());
			projetoBD.setAlunos(projeto.getAlunos());
			
			projetoRepository.save(projetoBD);
			return projetoBD;
		}
		else {
			throw new RuntimeException("Não foi possível atualizar o projeto informado");
		}
	}
	
	public Projeto addAluno(Optional<Aluno> aluno, Long id) {
		Optional<Projeto> projeto = getProjetoById(id);
		
		if (aluno.isPresent() && projeto.isPresent()) {
			Projeto projetoBD = projeto.get();
			Aluno alunoBD = aluno.get();
			
			projetoBD.getAlunos().add(alunoBD);
			projetoRepository.save(projetoBD);
			
			return projetoBD;
		}
		else {
			throw new RuntimeException("Não foi possível adicionar este aluno no projeto!!");
		}
	}
	

	public Projeto alterarProfessor(Optional<Professor> professor, Long id) {
		Optional<Projeto> projeto = getProjetoById(id);
		
		if (professor.isPresent() && projeto.isPresent()) {
			Projeto projetoBD = projeto.get();
			Professor professorBD = professor.get();
			
			projetoBD.setProfessorResponsavel(professorBD);
			projetoRepository.save(projetoBD);

			return projetoBD;
		}
		else {
			throw new RuntimeException("Não foi possível colocar este professor no projeto!!");
		}
	}
	
	public void remover(Long id) {
		Optional<Projeto> projeto = getProjetoById(id);
		if(projeto.isPresent()) {
			projetoRepository.deleteById(id);
		}
	}

}