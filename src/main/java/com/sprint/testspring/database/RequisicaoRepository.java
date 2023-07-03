package com.sprint.testspring.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.testspring.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
