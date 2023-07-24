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

        @Query("SELECT CONCAT('reqID: ', r.reqID), CONCAT('requisitante: ', r.requisitante), " +
                        "CONCAT('cliente: ', r.cliente), " +
                        "CONCAT('quantidade de itens: ', " +
                        "(SELECT COUNT(r2.item) FROM Requisicao r2 WHERE r2.reqID = r.reqID AND r2.status NOT LIKE 'Separado')),"
                        +
                        "r.dataRequisicao, " +
                        "CASE " +
                        "WHEN (SELECT COUNT(r3.status) FROM Requisicao r3 WHERE r3.reqID = r.reqID AND r3.status = 'Em separação') > 0 "
                        +
                        "THEN 'Em separação' " +
                        "WHEN (SELECT COUNT(r3.status) FROM Requisicao r3 WHERE r3.reqID = r.reqID AND r3.status = 'Pendente') = COUNT(r.status) "
                        +
                        "THEN 'Pendente' " +
                        "WHEN (SELECT COUNT(r3.status) FROM Requisicao r3 WHERE r3.reqID = r.reqID AND r3.status = 'Separado') = COUNT(r.status) "
                        +
                        "THEN 'Separado' " +
                        "END AS status, " +
                        "r.observacao, " +
                        "(SELECT COUNT(r3.item) FROM Requisicao r3 WHERE r3.reqID = r.reqID) " +
                        "FROM Requisicao r " +
                        "WHERE r.status NOT LIKE 'Baixa OK' " +
                        "GROUP BY r.reqID, r.requisitante, r.cliente, r.dataRequisicao, r.observacao")
        List<Object[]> obterRequisicoesPorStatus();

        @Query("SELECT CONCAT('reqID: ', r.reqID), CONCAT('requisitante: ', r.requisitante), CONCAT('cliente: ', r.cliente), CONCAT('quantidade de itens: ', COUNT(r.item)),  r.dataRequisicao  "
                        +
                        "FROM Requisicao r " +
                        "GROUP BY r.reqID, r.requisitante, r.cliente, r.dataRequisicao")
        List<Object[]> obterRequisicoes();

        @Query("SELECT COALESCE(MAX(reqID), 0) FROM Requisicao")
        int listarUltimoReqID();

        @Query("SELECT r FROM Requisicao r " +
                        "WHERE r.status NOT LIKE 'Baixa OK' " +
                        "AND r.reqID = :reqID ")
        List<Requisicao> obterRequisicoesPendentesPorReqID(@Param("reqID") Long reqID);

}
