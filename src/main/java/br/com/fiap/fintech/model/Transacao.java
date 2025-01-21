package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Transacao {
    private int codigo;
    private String nome;
    private double valor;
    private LocalDate dataTransacao;
    private TipoTransacao tipoTransacao;

    public Transacao(){}

    public Transacao(int codigo, String nome, double valor, LocalDate dataTransacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}

