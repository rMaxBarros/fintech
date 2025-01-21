package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.TransacaoDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Transacao;

import java.time.LocalDate;
import java.util.List;

public class TransacaoDaoTeste {
    public static void main(String[] args) {

        //Cadastrar uma transação
        TransacaoDao dao = DaoFactory.getTransacaoDao();

        Transacao transacao = new Transacao(
                0,
                "Pagamento Recebido",
                1000.0,
                LocalDate.of(2024,11,13)
        );

//        try {
//            dao.cadastrarTransacao(transacao);
//        } catch (DBException e) {
//            throw new RuntimeException(e);
//        }

        //Atualizando uma transação pelo código
//        transacao = dao.buscar(1);
//        transacao.setNome("Compras da semana");
//        transacao.setValor(119.99);
//        try{
//            dao.atualizarTransacao(transacao);
//        } catch (DBException e) {
//            e.printStackTrace();
//        }

        //Listar os produtos
        List<Transacao> lista = dao.listar();
        for(Transacao t : lista){
            System.out.println(
                    t.getNome() + " - " +
                            t.getValor() + " " +
                            t.getDataTransacao()
            );
        }

        //Remover
        try {
            dao.removerTransacao(3);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
