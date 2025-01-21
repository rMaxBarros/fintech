package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.TransacaoDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.TipoTransacao;
import br.com.fiap.fintech.model.Transacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDao implements TransacaoDao {

    private Connection con;

    @Override
    public void cadastrarTransacao(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        //Conexão com o banco de dados Oracle
        con = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO TB_FNT_TRANSACAO " +
                "(COD_TRANSACAO, NOME_TRANSACAO, " +
                "VALOR_TRANSACAO, DATA_TRANSACAO, " +
                "COD_TIPO_TRANSACAO) " +
                "VALUES (SQ_TB_FNT_TRANSACAO.NEXTVAL, ?, ?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, transacao.getNome());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getDataTransacao()));
            stmt.setInt(4, transacao.getTipoTransacao().getCodigoTipo());

            stmt.executeUpdate();

            System.out.println("Transação cadastrada com sucesso!");

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a transação.");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarTransacao(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        try {
            con = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_FNT_TRANSACAO SET " +
                    "NOME_TRANSACAO = ?, " +
                    "VALOR_TRANSACAO = ?, " +
                    "DATA_TRANSACAO = ?, " +
                    "COD_TIPO_TRANSACAO = ?" +
                    "WHERE COD_TRANSACAO = ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, transacao.getNome())  ;
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getDataTransacao()));
            stmt.setInt(4, transacao.getTipoTransacao().getCodigoTipo());
            stmt.setInt(5, transacao.getCodigo());

            stmt.executeUpdate();

            System.out.println("Transação atualizada");

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar a transação");

        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removerTransacao(int codigo) throws DBException {

        PreparedStatement stmt = null;

        try {
            con = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM TB_FNT_TRANSACAO WHERE COD_TRANSACAO = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("Transação removida.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Transacao buscar(int codigo) {
        Transacao transacao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_FNT_TRANSACAO " +
                    "INNER JOIN TB_FNT_TIPO_TRANSACAO " +
                    "ON TB_FNT_TRANSACAO.COD_TIPO_TRANSACAO = TB_FNT_TIPO_TRANSACAO.COD_TIPO_TRANSACAO " +
                    "WHERE TB_FNT_TRANSACAO.COD_TRANSACAO = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigoRetornado = rs.getInt("COD_TRANSACAO");
                String nome = rs.getString("NOME_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO").toLocalDate();

                int codigoTipo = rs.getInt("COD_TIPO_TRANSACAO");
                String nomeTipo = rs.getString("NOME_TIPO_TRANSACAO");

                transacao = new Transacao(codigoRetornado, nome, valor, data);

                TipoTransacao tipo = new TipoTransacao(codigoTipo, nomeTipo);

                transacao.setTipoTransacao(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transacao;
    }

    @Override
    public List<Transacao> listar() {

        List<Transacao> lista = new ArrayList<Transacao>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_FNT_TRANSACAO " +
                    "INNER JOIN TB_FNT_TIPO_TRANSACAO " +
                    "ON TB_FNT_TRANSACAO.COD_TIPO_TRANSACAO =" +
                    " TB_FNT_TIPO_TRANSACAO.COD_TIPO_TRANSACAO";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("COD_TRANSACAO");
                String nome = rs.getString("NOME_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                //Transformando o Date em LocalDate
                //java.sql.Date data = rs.getDate("DATA_TRANSACAO");
                LocalDate dataTransacao = rs.getDate("DATA_TRANSACAO")
                        .toLocalDate();

                int codigoTipo = rs.getInt("COD_TIPO_TRANSACAO");
                String nomeTipo = rs.getString("NOME_TIPO_TRANSACAO");

                Transacao transacao = new Transacao(codigo, nome, valor, dataTransacao);
                TipoTransacao tipo = new TipoTransacao(codigoTipo, nomeTipo);
                transacao.setTipoTransacao(tipo);

                lista.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
