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

import com.projeto_1.domain.Aluno;
import com.projeto_1.domain.Professor;
import com.projeto_1.domain.Projeto;
import com.projeto_1.dto.ProjetoDTO;
import com.projeto_1.service.AlunoService;
import com.projeto_1.service.ProfessorService;
import com.projeto_1.service.ProjetoService;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetosController {
	
	@Autowired
	private ProjetoService service;
	
	@Autowired
	private AlunoService serviceAluno;
	
	@Autowired
	private ProfessorService serviceProfessor;
	
	@GetMapping
	public ResponseEntity<List<ProjetoDTO>> get() {
		return ResponseEntity.ok(service.getProjetos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> get(@PathVariable("id") Long id) {
		Optional<Projeto> projeto = service.getProjetoById(id);
		return projeto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/area/{area}")
	public ResponseEntity<List<ProjetoDTO>> getProjetosByArea(@PathVariable("area") String area) {
		List<ProjetoDTO> listaProjetos = service.getProjetosByArea(area);
		return listaProjetos.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(listaProjetos);
	}
	
	@PostMapping
	public String cadastrarProjeto(@RequestBody Projeto projeto) {
		Projeto c = service.cadastrar(projeto);
		return "Projeto Salvo!!: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String atualizarProjeto(@PathVariable("id") Long id, @RequestBody Projeto projeto) {
		Projeto c = service.atualizar(projeto, id);
		return "Projeto Editado!!: " + c.getId();
	}
	
	@PutMapping("/{id}/aluno/{id_aluno}")
	public String addAluno(@PathVariable("id") Long id, @PathVariable("id_aluno") Long id_aluno) {
		Optional<Aluno> aluno = serviceAluno.getAlunoById(id_aluno);
		service.addAluno(aluno, id);
		return "Aluno adicionado ao projeto";
	}
	
	@PutMapping("/{id}/professor/{id_professor}")
	public String alterarProfessor(@PathVariable("id") Long id, @PathVariable("id_professor") Long id_professor) {
		Optional<Professor> professor = serviceProfessor.getProfessorById(id_professor);
		service.alterarProfessor(professor, id);
		return "Professor adicionado ao projeto";
	}
	@DeleteMapping("/{id}")
	public String removerProjeto(@PathVariable("id") Long id) {
		service.remover(id);
		return "Projeto Excluído!!.";
	}
	
}