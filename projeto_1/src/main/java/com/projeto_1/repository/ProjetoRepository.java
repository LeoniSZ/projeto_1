package com.projeto_1.repository;

import java.util.List;

import com.projeto_1.domain.Projeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	
	List<Projeto> findByArea(String area);
}