package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void cadastrarTransacao(Transacao transacao) throws DBException;
    void atualizarTransacao(Transacao transacao) throws DBException;
    void removerTransacao(int codigo) throws DBException;
    Transacao buscar(int id);
    List<Transacao> listar();

}
