package com.sprint.testspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Requisicoes")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long reqID;

    private String item;

    private String descricao;

    private Integer quantidade;

    private String kit;

    private String requisitante;

    private String status;

    private String cliente;

    private Integer quantidadeSeparada;

    private String dataRequisicao;

    private String dataSeparacao;

    private String dataBaixa;

    private String separadoPor;

    private String baixaPor;

    private String prateleira;

    private String observacao;

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReqID() {
        return reqID;
    }

    public void setReqID(Long reqID) {
        this.reqID = reqID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(String requisitante) {
        this.requisitante = requisitante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getQuantidadeSeparada() {
        return quantidadeSeparada;
    }

    public void setQuantidadeSeparada(Integer quantidadeSeparada) {
        this.quantidadeSeparada = quantidadeSeparada;
    }

    public String getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(String dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public String getDataSeparacao() {
        return dataSeparacao;
    }

    public void setDataSeparacao(String dataSeparacao) {
        this.dataSeparacao = dataSeparacao;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public String getSeparadoPor() {
        return separadoPor;
    }

    public void setSeparadoPor(String separadoPor) {
        this.separadoPor = separadoPor;
    }

    public String getBaixaPor() {
        return baixaPor;
    }

    public void setBaixaPor(String baixaPor) {
        this.baixaPor = baixaPor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
