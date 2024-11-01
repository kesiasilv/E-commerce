# Operações de Cadastro e Gerenciamento - Tabela Clientes - Sistema E-commerce

Este projeto tem como objetivo implementar as operações de Cadastro, Consulta, Atualização e Remoção (CRUD) para a tabela de clientes de um sistema de comércio eletrônico, desenvolvido como parte da disciplina de Banco de Dados.

## Descrição
As operações foram aplicadas na tabela de clientes para possibilitar a criação de registros, consulta de dados, atualização de informações e exclusão de registros. Essas operações permitem um gerenciamento completo dos dados dos clientes, promovendo a manutenção e integridade dos registros em um ambiente de comércio eletrônico.

## Funcionalidades
- **Cadastro (Create)**: Insere novos clientes na tabela de clientes.
- **Consulta (Read)**: Permite visualizar informações detalhadas dos clientes.
- **Atualização (Update)**: Atualiza dados específicos dos clientes, conforme necessário.
- **Remoção (Delete)**: Exclui clientes da tabela.

## Conexão Java com SQL
O projeto faz uso da integração entre Java e MySQL para a execução das operações CRUD diretamente na aplicação Java, utilizando o JDBC (Java Database Connectivity). A conexão é realizada conforme o seguinte processo:
1. **Driver JDBC**: Utilização do driver JDBC do MySQL para permitir a comunicação entre Java e o banco de dados.
2. **Configuração da Conexão**: O código define as informações de conexão, incluindo URL do banco, nome de usuário e senha.
3. **Execução de Operações SQL**: Através de comandos SQL, as operações de Cadastro, Consulta, Atualização e Remoção são enviadas para o banco de dados a partir da aplicação Java.

## Testes Realizados
Os testes foram realizados para garantir que todas as operações funcionem corretamente. A pasta de teste está disponível no repositório, contendo imagens e registros dos testes realizados.

## Configuração de Conexão JDBC
![conexao1](https://github.com/user-attachments/assets/dc98db42-9d5b-425e-9067-babf90322cbc)
![conexao](https://github.com/user-attachments/assets/52690447-c28e-4f5f-b878-449066ced28b)

## Tecnologias Utilizadas
- **Banco de Dados MySQL**
- **Linguagem SQL** para comandos DML (Data Manipulation Language)
- **Linguagem de Programação Java**
- **JDBC (Java Database Connectivity)** para integração com o banco de dados

## Estrutura do Projeto
1. Script SQL para criação da tabela de clientes com todos os campos necessários.
2. Implementação dos comandos de Cadastro, Consulta, Atualização e Remoção.
3. Conexão JDBC entre Java e MySQL.
4. Exemplos de comandos SQL utilizados para cada operação, organizados em blocos de código.
