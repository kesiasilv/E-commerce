/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADO;

/**
 *
 * @author kesia.viana
 */


import Sistema.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteADO {
    private Connection connection;

    public ClienteADO(String url, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO `e-commerce`.`Clientes` (Nome, Email, Telefone, Data_cadastro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setDate(4, cliente.getDataCadastro());
            stmt.executeUpdate();
        }
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM `e-commerce`.`Clientes`";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("Nome"),
                        rs.getString("Email"),
                        rs.getString("Telefone"),
                        rs.getDate("Data_cadastro")
                ));
            }
        }
        return clientes;
    }

    public Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM `e-commerce`.`Clientes` WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("Nome"),
                            rs.getString("Email"),
                            rs.getString("Telefone"),
                            rs.getDate("Data_cadastro")
                    );
                }
            }
        }
        return null;
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE `e-commerce`.`Clientes` SET Nome = ?, Email = ?, Telefone = ? WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM `e-commerce`.`Clientes` WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void fecharConexao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
