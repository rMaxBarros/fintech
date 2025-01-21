package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.TipoTransacaoDao;
import br.com.fiap.fintech.dao.TransacaoDao;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.dao.impl.OracleTipoTransacaoDao;
import br.com.fiap.fintech.dao.impl.OracleTransacaoDao;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDao;

public class DaoFactory {

    public static TransacaoDao getTransacaoDao() {
        return new OracleTransacaoDao();
    }

    public static TipoTransacaoDao getTipoTransacaoDao() {
        return new OracleTipoTransacaoDao();
    }

    public static UsuarioDao getUsuarioDao() {
        return new OracleUsuarioDao();
    }
}
