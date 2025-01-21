package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.TipoTransacaoDao;
import br.com.fiap.fintech.model.TipoTransacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTipoTransacaoDao implements TipoTransacaoDao {

    private Connection conexao;

    @Override
    public List<TipoTransacao> listar() {
        List<TipoTransacao> lista = new ArrayList<TipoTransacao>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            stmt = conexao.prepareStatement("SELECT * FROM TB_FNT_TIPO_TRANSACAO");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("COD_TIPO_TRANSACAO");
                String tipo = rs.getString("NOME_TIPO_TRANSACAO");
                TipoTransacao tipoTransacao = new TipoTransacao(codigo, tipo);
                lista.add(tipoTransacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
