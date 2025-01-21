package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.TipoTransacaoDao;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.TipoTransacao;

import java.util.List;

public class TipoTransacaoDaoTeste {
    public static void main(String[] args) {
        TipoTransacaoDao dao = DaoFactory.getTipoTransacaoDao();

        List<TipoTransacao> lista = dao.listar();
        for (TipoTransacao tipo : lista) {
            System.out.println(tipo.getCodigoTipo() + " " + tipo.getNomeTipo());
        }
    }
}
