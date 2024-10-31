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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteADO {
    private Connection conexao;
    
     // Construtor que recebe a conexão
    public ClienteADO(Connection conexao) {
        this.conexao = conexao;
    }
    
    //Create cadastro cliente
    public boolean cadastrar(Cliente cliente) {
    String sql = "INSERT INTO clientes (Nome, Email, Telefone, Data_Cadastro) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, cliente.getId_cliente());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getEmail());
        ps.setFloat(4, cliente.getTelefone());
        ps.setDate(5, java.sql.Date.valueOf(cliente.getDataCadastro())); // Convert LocalDate to java.sql.Date
        ps.executeUpdate();
        
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }
    
    public List<Cliente> leitura(){
      List<Cliente> clientesList = new ArrayList <>();
      String sql = "SELECT * FROM clientes";
      
      try(Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
          while(rs.next()){
              Cliente cliente = new Cliente();
              cliente.setId_cliente(rs.getString("idCliente"));
              cliente.setNome(rs.getString("Nome"));
              cliente.setEmail(rs.getString("Email"));
              cliente.setTelefone(rs.getFloat("Telefone"));
              cliente.setDataCadastro(rs.getDate("Data_cadastro").toLocalDate());
              clientesList.add(cliente);
          }
      } catch (SQLException e){
              e.printStackTrace();
        }
        return clientesList;
    }
}

public boolean atualizar(Cliente cliente){
    String sql = "UPDATE Clientes SET Nome = ?, Email = ?, Telefone = ? WHERE idCliente = ?";
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEmail());
        ps.setString(3, cliente.getTelefone());
        ps.setInt(4, cliente.getIdCliente());
        ps.executeUpdate();
        return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean excluir(int idCliente){
    String sql = "DELETE FROM Clientes WHERE idCliente = ?";
    try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        ps.setInt(1, idCliente);
        ps.executeUpdate();
        return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

