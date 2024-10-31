/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kesia.viana
 *//*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author kesia.viana
 */
public class Ecommerce {
    private static final String URL = "jdbc:mysql://localhost:3307/e-commerce"; // URL correta do banco de dados
    private static final String USUARIO = "root"; // Nome de usuário do MySQL
    private static final String SENHA = "catolica"; // Senha do MySQL
     
    public static void main(String[] args) {
        Connection conexao = null;

        // Estabelecendo a conexão
        conexao = getConnection();
        System.out.println("Conexão estabelecida com sucesso!");

        // Fechando a conexão
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
        
        // Operando usuários
        // UsuariosDAO usuarioDAO = new UsuariosDAO(conexao);
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados", e);
        }
    }
}
