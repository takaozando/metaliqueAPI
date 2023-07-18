package com.sprint.testspring.controller;

import org.springframework.web.bind.annotation.*;

import com.sprint.testspring.database.RequisicaoRepository;
import com.sprint.testspring.model.Requisicao;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/requisicoes")
@CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin here
public class RequisicaoController {

    private final RequisicaoRepository requisicaoRepository;

    public RequisicaoController(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    @GetMapping("/all")
    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    @GetMapping("/historico")
    public List<Object[]> obterQuantidadeItensPorRequisicao() {
        return requisicaoRepository.obterQuantidadeItensPorRequisicao();
    }

    @GetMapping("/pendentes")
    public List<Object[]> listarRequisicoesPorStatus() {
        System.out.println(requisicaoRepository.obterRequisicoesPorStatus());
        return requisicaoRepository.obterRequisicoesPorStatus();
    }

    @Transactional
    public void marcarSeparadoPorID(Long reqID, String novoStatus, String novaDataSeparacao,
            String nomeAlmoxarife) {
        List<Requisicao> requisicoes = requisicaoRepository.findByReqID(reqID);
        for (Requisicao requisicao : requisicoes) {
            requisicao.setStatus(novoStatus);
            requisicao.setDataSeparacao(novaDataSeparacao);
            requisicao.setSeparadoPor(nomeAlmoxarife);
        }
        requisicaoRepository.saveAll(requisicoes);
    }

    @PostMapping("/post")
    public List<Requisicao> criarRequisicao(@RequestBody List<Requisicao> requisicoes) {
        return requisicaoRepository.saveAll(requisicoes);
    }

    @GetMapping("/get/{reqID}")
    public List<Requisicao> listarRequisicoesPorReqID(@PathVariable Long reqID) {
        return requisicaoRepository.findByReqID(reqID);
    }

    @GetMapping("/getPending/{reqID}")
    public List<Requisicao> listarRequisicoesPendentesPorReqID(@PathVariable Long reqID) {
        return requisicaoRepository.obterRequisicoesPendentesPorReqID(reqID);
    }

    @GetMapping("/get/lastReqID")
    public int listarUltimoReqID() {
        return requisicaoRepository.listarUltimoReqID();
    }

    @PutMapping("/update/{reqId}")
    public void atualizarRequisicao(@PathVariable Long reqId,
            @RequestBody List<Requisicao> requisicoesAtualizadas) {

        for (Requisicao requisicao : requisicoesAtualizadas) {
            System.out.println("requisicao atualizada: " + requisicao.getQuantidadeSeparada());
            if (requisicao.getQuantidadeSeparada() == null) {
                requisicao.setStatus("Pendente");
            } else if (requisicao.getQuantidade() <= requisicao.getQuantidadeSeparada()) {
                requisicao.setStatus("Separado");
            } else {
                requisicao.setStatus("Pendente");
            }
            requisicao.setQuantidadeSeparada(requisicao.getQuantidadeSeparada());
            requisicao.setDataSeparacao(LocalDateTime.now().toString());
            requisicao.setSeparadoPor(requisicao.getSeparadoPor());
        }
        requisicaoRepository.saveAll(requisicoesAtualizadas);
    }

    @PutMapping("/separar/{id}")
    public Requisicao gerarSeparacao(@PathVariable Long id, @RequestBody Requisicao requisicaoAtualizada) {
        Requisicao requisicao = requisicaoRepository.findById(id).orElse(null);
        if (requisicao != null) {
            // Atualizar os campos da requisicao com os valores da requisicaoAtualizada
            requisicao.setStatus(requisicaoAtualizada.getStatus());
            requisicao.setQuantidadeSeparada(requisicaoAtualizada.getQuantidadeSeparada());
            requisicao.setDataSeparacao(requisicaoAtualizada.getDataSeparacao());
            requisicao.setSeparadoPor(requisicaoAtualizada.getSeparadoPor());

            return requisicaoRepository.save(requisicao);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deletarRequisicao(@PathVariable Long id) {
        requisicaoRepository.deleteById(id);
    }
}
