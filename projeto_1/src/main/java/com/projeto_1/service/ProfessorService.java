package com.projeto_1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.projeto_1.domain.Professor;
import com.projeto_1.dto.ProfessorDTO;
import com.projeto_1.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<ProfessorDTO> getProfessores(){
		return professorRepository.findAll().stream().map(ProfessorDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Professor> getProfessorById(Long id) {
		return professorRepository.findById(id);
	}
	
	public List<ProfessorDTO> getProfessorByMatricula(String matricula) {
		return professorRepository.findByMatricula(matricula).stream().map(ProfessorDTO::new).collect(Collectors.toList());
	}
	
	public List<ProfessorDTO> getProfessorByCurso(String curso) {
		return professorRepository.findByCurso(curso).stream().map(ProfessorDTO::new).collect(Collectors.toList());
	}
	
	public Professor cadastrar(Professor professor) {
		return professorRepository.save(professor);
	}
	
	public Professor atualizar(Professor professor, Long id) {
		
		Optional<Professor> optional = getProfessorById(id);
		if (optional.isPresent()) {
			Professor professorBD = optional.get();
			professorBD.setMatricula(professor.getMatricula());
			professorBD.setNome(professor.getNome());
			professorBD.setCurso(professor.getCurso());
			
			professorRepository.save(professorBD);
			return professorBD;
		}
		else {
			throw new RuntimeException("Não foi possível Editar este Professor");
		}
	}
	
	public void remover(Long id) {
		Optional<Professor> professor = getProfessorById(id);
		if(professor.isPresent()) {
			professorRepository.deleteById(id);
		}
	}

}