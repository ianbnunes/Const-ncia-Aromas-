package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.config.DatabaseConnection;
import com.cesarschool.constancia.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    // Retorna todos os clientes
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setTelefone(resultSet.getInt("fk_telefone_telefone_PK"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setSenha(resultSet.getString("senha"));
                cliente.setEndereco(resultSet.getInt("fk_endereco_endereco_PK"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // Busca um cliente pelo CPF
    public Cliente getClienteByCpf(String cpf) throws SQLException {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setTelefone(resultSet.getInt("fk_telefone_telefone_PK"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setSenha(resultSet.getString("senha"));
                    cliente.setEndereco(resultSet.getInt("fk_endereco_endereco_PK"));
                }
            }
        }
        return cliente;
    }

    // Adiciona um novo cliente
    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (cpf, nome, fk_telefone_telefone_PK, email, senha, fk_endereco_endereco_PK) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            statement.setInt(3, cliente.getTelefone());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getSenha());
            statement.setInt(6, cliente.getEndereco());
            statement.executeUpdate();
        }
    }

    // Atualiza um cliente existente
    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nome = ?, fk_telefone_telefone_PK = ?, email = ?, senha = ?, fk_endereco_endereco_PK = ? WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Define os valores para a consulta
            statement.setString(1, cliente.getNome());
            statement.setInt(2, cliente.getTelefone());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSenha());
            statement.setInt(5, cliente.getEndereco());
            statement.setString(6, cliente.getCpf());

            // Executa a consulta
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Erro: Nenhum cliente encontrado com o CPF especificado.");
            }
        }
    }

    // Deleta um cliente pelo CPF
    public void deleteCliente(String cpf) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            statement.executeUpdate();
        }
    }

    // Verifica se um cliente existe pelo CPF
    public boolean existsClienteByCpf(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    // Busca o CPF de um cliente pelo email
    public Optional<String> getCpfByEmail(String email) throws SQLException {
        String sql = "SELECT cpf FROM Cliente WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getString("cpf"));
                }
            }
        }
        return Optional.empty();
    }
}
