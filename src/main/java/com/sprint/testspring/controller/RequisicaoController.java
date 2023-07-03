package com.sprint.testspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.testspring.database.RequisicaoRepository;
import com.sprint.testspring.model.Requisicao;

import java.util.List;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    private final RequisicaoRepository requisicaoRepository;

    @Autowired
    public RequisicaoController(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    @GetMapping("/all")
    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    @PostMapping("/post")
    public Requisicao criarRequisicao(@RequestBody Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    @GetMapping("/get/{id}")
    public Requisicao buscarRequisicaoPorId(@PathVariable Long id) {
        return requisicaoRepository.findById(id).orElse(null);
    }

    @PutMapping("/put/{id}")
    public Requisicao atualizarRequisicao(@PathVariable Long id, @RequestBody Requisicao requisicaoAtualizada) {
        Requisicao requisicao = requisicaoRepository.findById(id).orElse(null);
        if (requisicao != null) {
            // Atualizar os campos da requisicao com os valores da requisicaoAtualizada
            requisicao.setReqID(requisicaoAtualizada.getReqID());
            requisicao.setItem(requisicaoAtualizada.getItem());
            requisicao.setDescricao(requisicaoAtualizada.getDescricao());
            requisicao.setQuantidade(requisicaoAtualizada.getQuantidade());
            requisicao.setKit(requisicaoAtualizada.getKit());
            requisicao.setRequisitante(requisicaoAtualizada.getRequisitante());
            requisicao.setStatus(requisicaoAtualizada.getStatus());
            requisicao.setCliente(requisicaoAtualizada.getCliente());
            requisicao.setQuantidadeSeparada(requisicaoAtualizada.getQuantidadeSeparada());
            requisicao.setDataRequisicao(requisicaoAtualizada.getDataRequisicao());
            requisicao.setDataSeparacao(requisicaoAtualizada.getDataSeparacao());
            requisicao.setDataBaixa(requisicaoAtualizada.getDataBaixa());
            requisicao.setSeparadoPor(requisicaoAtualizada.getSeparadoPor());
            requisicao.setBaixaPor(requisicaoAtualizada.getBaixaPor());

            return requisicaoRepository.save(requisicao);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deletarRequisicao(@PathVariable Long id) {
        requisicaoRepository.deleteById(id);
    }
}
