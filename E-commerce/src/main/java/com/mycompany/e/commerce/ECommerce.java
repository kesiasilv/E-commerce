/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.e.commerce;

/**
 *
 * @author kesia.viana
 *//*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import ADO.ClienteADO;
import Sistema.Cliente;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ECommerce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/e-commerce"; // URL do banco de dados
        String user = "root"; // Atualize com seu usuário
        String password = "c@tolic@"; // Atualize com sua senha

        try {
            ClienteADO clienteADO = new ClienteADO(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
            int opcao;
        //menu de opcoes:
            do {
                System.out.println("\n---Escolha uma opção:---");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Consultar Clientes");
                System.out.println("3. Consultar Cliente por ID");
                System.out.println("4. Atualizar Cliente");
                System.out.println("5. Excluir Cliente");
                System.out.println("6. Sair");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("E-mail: ");
                        String email = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        java.sql.Date dataCadastro = new java.sql.Date(System.currentTimeMillis());
                        Cliente novoCliente = new Cliente(0, nome, email, telefone, dataCadastro);
                        clienteADO.cadastrarCliente(novoCliente);
                        System.out.println("\nCliente cadastrado com sucesso!");
                        break;


                    case 2:
                        List<Cliente> clientes = clienteADO.listarClientes();
                        System.out.println("\nClientes cadastrados:");
                        for (Cliente c : clientes) {
                            System.out.println(c.getIdCliente() + " - " + c.getNome() + " - " + c.getEmail());
                        }
                        break;

                    case 3:
                        System.out.print("Digite o ID do cliente: ");
                        int id = scanner.nextInt();
                        Cliente cliente = clienteADO.buscarClientePorId(id);
                        if (cliente != null) {
                            System.out.println("\n---------------------");
                            System.out.println("\nDetalhes do Cliente:");
                            System.out.println("\n---------------------");
                            System.out.println("Nome: " + cliente.getNome());
                            System.out.println("E-mail: " + cliente.getEmail());
                            System.out.println("Telefone: " + cliente.getTelefone());
                        } else {
                            System.out.println("\nCliente não encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o ID do cliente a ser atualizado: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer
                        Cliente clienteAtualizar = clienteADO.buscarClientePorId(idAtualizar);
                        if (clienteAtualizar != null) {
                            System.out.println("Nome atual: " + clienteAtualizar.getNome());
                            System.out.print("Novo Nome: ");
                            clienteAtualizar.setNome(scanner.nextLine());
                            System.out.print("Novo E-mail: ");
                            clienteAtualizar.setEmail(scanner.nextLine());
                            System.out.print("Novo Telefone: ");
                            clienteAtualizar.setTelefone(scanner.nextLine());
                            clienteADO.atualizarCliente(clienteAtualizar);
                            System.out.println("\n-----Cliente atualizado com sucesso!-----");
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o ID do cliente a ser excluído: ");
                        int idExcluir = scanner.nextInt();
                        clienteADO.excluirCliente(idExcluir);
                        System.out.println("\nCliente excluído com sucesso!");
                        break;

                    case 6:
                        clienteADO.fecharConexao();
                        System.out.println("\nSaindo...");
                        break;

                    default:
                        System.out.println("---\nOpção inválida!---");
                }
            } while (opcao != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

