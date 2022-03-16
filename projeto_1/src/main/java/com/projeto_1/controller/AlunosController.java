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
import com.projeto_1.dto.AlunoDTO;
import com.projeto_1.service.AlunoService;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunosController {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> get() {
		return ResponseEntity.ok(service.getAlunos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> get(@PathVariable("id") Long id) {
		Optional<Aluno> aluno = service.getAlunoById(id);
		return aluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<List<AlunoDTO>> getAlunosByMatricula(@PathVariable("matricula") String matricula) {
		List<AlunoDTO> listaAlunos = service.getAlunoByMatricula(matricula);
		return listaAlunos.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(listaAlunos);
	}
	
	@GetMapping("/curso/{curso}")
	public ResponseEntity<List<AlunoDTO>> getAlunosByCurso(@PathVariable("curso") String curso) {
		List<AlunoDTO> listaAlunos = service.getAlunoByCurso(curso);
		return listaAlunos.isEmpty() ? 
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(listaAlunos);
	}
	
	@PostMapping
	public String cadastrarAluno(@RequestBody Aluno aluno) {
		Aluno c = service.cadastrar(aluno);
		return "Aluno Cadastrado!: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String atualizarAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
		Aluno c = service.atualizar(aluno, id);
		return "Aluno Editado!: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String removerAluno(@PathVariable("id") Long id) {
		service.remover(id);
		return "Aluno Excluído!.";
	}
	
}
