package com.sprint.testspring.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.testspring.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f.senha FROM Funcionario f WHERE f.nome LIKE %:nome%")
    String getUserPwd(@Param("nome") String nome);
}
