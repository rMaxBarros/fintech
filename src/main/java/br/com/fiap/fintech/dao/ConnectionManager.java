package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager con;

    //Retornando a instância da conexão
    public static ConnectionManager getInstance() {
        if (con == null) {
            con = new ConnectionManager();
        }
        return con;
    }

    //Criando a conexão com o banco
    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try {
                connection = DriverManager.getConnection(
                        "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                        "RM557855",
                        "190299"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }
}
