package com.projeto_1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.projeto_1.domain.Aluno;
import com.projeto_1.dto.AlunoDTO;
import com.projeto_1.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<AlunoDTO> getAlunos(){
		return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Aluno> getAlunoById(Long id) {
		return alunoRepository.findById(id);
	}
	
	public List<AlunoDTO> getAlunoByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula).stream().map(AlunoDTO::new).collect(Collectors.toList());
	}
	
	public List<AlunoDTO> getAlunoByCurso(String curso) {
		return alunoRepository.findByCurso(curso).stream().map(AlunoDTO::new).collect(Collectors.toList());
	}
	
	public Aluno cadastrar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public Aluno atualizar(Aluno aluno, Long id) {
		
		Optional<Aluno> optional = getAlunoById(id);
		if (optional.isPresent()) {
			Aluno alunoBD = optional.get();
			alunoBD.setMatricula(aluno.getMatricula());
			alunoBD.setNome(aluno.getNome());
			alunoBD.setCpf(aluno.getCpf());
			alunoBD.setCurso(aluno.getCurso());
			
			alunoRepository.save(alunoBD);
			return alunoBD;
		}
		else {
			throw new RuntimeException("Não foi possível editar este aluno!!");
		}
	}
	
	public void remover(Long id) {
		Optional<Aluno> aluno = getAlunoById(id);
		if(aluno.isPresent()) {
			alunoRepository.deleteById(id);
		}
	}

}
