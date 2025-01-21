package br.com.fiap.fintech.model;

public class TipoTransacao {
    private int codigoTipo;
    private String nomeTipo;

    public TipoTransacao() {
    }
    public TipoTransacao(int codigoTipo, String nomeTipo) {
        this.codigoTipo = codigoTipo;
        this.nomeTipo = nomeTipo;
    }

    public int getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
