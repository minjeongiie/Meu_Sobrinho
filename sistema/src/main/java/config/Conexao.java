package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL =
            System.getenv("DB_URL") != null
                    ? System.getenv("DB_URL")
                    : "jdbc:mysql://localhost:3306/meu_sobrinho";

    private static final String USUARIO =
            System.getenv("DB_USER") != null
                    ? System.getenv("DB_USER")
                    : "meusobrinho";

    private static final String SENHA =
            System.getenv("DB_PASSWORD") != null
                    ? System.getenv("DB_PASSWORD")
                    : "125346kj";

    public static Connection conectar()
            throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Driver MySQL não encontrado.",
                    e
            );
        }

        return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
        );
    }
}