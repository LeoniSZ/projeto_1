package com.projeto_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_1.domain.Professor;
import com.projeto_1.dto.ProfessorDTO;
import com.projeto_1.service.ProfessorService;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessoresController {
	
	@Autowired
	private ProfessorService service;
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> get() {
		return ResponseEntity.ok(service.getProfessores());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Professor> get(@PathVariable("id") Long id) {
		Optional<Professor> professor = service.getProfessorById(id);
		return professor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<List<ProfessorDTO>> getProfessoresByMatricula(@PathVariable("matricula") String matricula) {
		List<ProfessorDTO> listaProfessores = service.getProfessorByMatricula(matricula);
		return listaProfessores.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(listaProfessores);
	}
	
	@GetMapping("/curso/{curso}")
	public ResponseEntity<List<ProfessorDTO>> getProfessoresByCurso(@PathVariable("curso") String curso) {
		List<ProfessorDTO> listaProfessores = service.getProfessorByCurso(curso);
		return listaProfessores.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(listaProfessores);
	}
	
	@PostMapping
	public String cadastrarProfessor(@RequestBody Professor professor) {
		Professor c = service.cadastrar(professor);
		return "Professor Adicionado!!: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String atualizarProfessor(@PathVariable("id") Long id, @RequestBody Professor professor) {
		Professor c = service.atualizar(professor, id);
		return "Professor Editado!!: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String removerProfessor(@PathVariable("id") Long id) {
		service.remover(id);
		return "Professor Excluído!!.";
	}
	
}