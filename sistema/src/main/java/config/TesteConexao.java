package config;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try (Connection connection = Conexao.conectar()) {

            System.out.println("Conectou com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}