package com.sprint.testspring.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.testspring.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
        List<Requisicao> findByStatus(String status);

        List<Requisicao> findByReqID(Long reqID);

        @Query("SELECT 'reqID: ' || r.reqID, 'requisitante: ' || r.requisitante, 'cliente: ' || r.cliente, 'quantidade de itens: '||  COUNT(r.item),  r.dataRequisicao,  'status: ' || r.status "
                        +
                        "FROM Requisicao r " +
                        "GROUP BY r.reqID, r.requisitante, r.cliente,r.dataRequisicao, r.status")
        List<Object[]> obterQuantidadeItensPorRequisicao();

        @Query("SELECT CONCAT('reqID: ', r.reqID), CONCAT('requisitante: ', r.requisitante), CONCAT('cliente: ', r.cliente), CONCAT('quantidade de itens: ', COUNT(r.item)), CONCAT('data requisição: ', r.dataRequisicao), CONCAT('status: ', r.status) "
                        +
                        "FROM Requisicao r " +
                        "WHERE r.status NOT IN ('Baixa OK', 'Ignorado') " +
                        "GROUP BY r.reqID, r.requisitante, r.cliente, r.dataRequisicao, r.status")
        List<Object[]> obterRequisicoesPorStatus();

        @Query("SELECT COALESCE(MAX(reqID), 0) FROM Requisicao")
        int listarUltimoReqID();

        @Query("SELECT r FROM Requisicao r " +
                        "WHERE r.status NOT IN ('Baixa OK', 'Ignorado', 'Separado') " +
                        "AND r.reqID = :reqID ")
        List<Requisicao> obterRequisicoesPendentesPorReqID(@Param("reqID") Long reqID);

}
