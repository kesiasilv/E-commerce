/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import Sistema.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteADO {
    private Connection conexao;

    public ClienteADO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean cadastrar(Cliente cliente) {
        
        /* Incluir validações básicas para garantir que os dados do cliente estejam corretos antes de serem inseridos ou atualizados no banco de dados.. */
        if (cliente.getNome() == null || cliente.getNome().isEmpty() ||
            cliente.getEmail() == null || cliente.getEmail().isEmpty() ||
            cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos.");
            return false;
        }
            
        String sql = "INSERT INTO clientes (Nome, Email, Telefone, Data_Cadastro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone()); // Mantenha como String
            ps.setDate(4, java.sql.Date.valueOf(cliente.getDataCadastro()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> leitura() {
        List<Cliente> clientesList = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getString("idCliente")); // Altere para String
                cliente.setNome(rs.getString("Nome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setTelefone(rs.getString("Telefone")); // Mantenha como String
                cliente.setDataCadastro(rs.getDate("Data_Cadastro").toLocalDate());
                clientesList.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientesList;
    }
    
    /*Incluir uma opção para pesquisar um cliente específico pelo cliente_id.*/
    public Cliente buscarPorId(String idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE idCliente = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId_cliente(rs.getString("idCliente")); // Altere para String
                cliente.setNome(rs.getString("Nome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setTelefone(rs.getString("Telefone")); // Mantenha como String
                cliente.setDataCadastro(rs.getDate("Data_Cadastro").toLocalDate());
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public boolean atualizar(Cliente cliente) {
        /*Permitir a atualização das informações de um cliente existente.
        O sistema deve solicitar o cliente_id e exibir os dados atuais do cliente antes de realizar a atualização.*/
        Cliente clienteAtual = buscarPorId(cliente.getId_cliente());
        if (clienteAtual != null) {
            System.out.println("Dados atuais do cliente:");
            System.out.println("Nome: " + clienteAtual.getNome());
            System.out.println("Email: " + clienteAtual.getEmail());
            System.out.println("Telefone: " + clienteAtual.getTelefone());
        }
        String sql = "UPDATE clientes SET Nome = ?, Email = ?, Telefone = ? WHERE idCliente = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone()); // Mantenha como String
            ps.setString(4, cliente.getId_cliente()); // Altere para String
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(String idCliente) { // Mantenha como String
        /*Implementar uma funcionalidade para deletar um cliente do sistema, solicitando o cliente_id como referência.*/
        System.out.println("Tem certeza que deseja excluir o cliente com ID: " + idCliente + "? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String confirmacao = scanner.nextLine();
        if (!confirmacao.equalsIgnoreCase("s")) {
            System.out.println("Exclusão cancelada.");
            return false;
        }
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
}


