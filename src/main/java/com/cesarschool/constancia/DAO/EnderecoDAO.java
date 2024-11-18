package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private final Connection connection;

    // Construtor para inicializar a conexão
    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um endereço
    public void inserirEndereco(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (enderecoPK, numero, rua, bairro) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, endereco.getEnderecoPK());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getRua());
            stmt.setString(4, endereco.getBairro());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um endereço
    public void atualizarEndereco(Endereco endereco) throws SQLException {
        String sql = "UPDATE Endereco SET numero = ?, rua = ?, bairro = ? " +
                "WHERE enderecoPK = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getNumero());
            stmt.setString(2, endereco.getRua());
            stmt.setString(3, endereco.getBairro());
            stmt.setInt(4, endereco.getEnderecoPK());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um endereço pelo ID
    public void excluirEndereco(int enderecoPK) throws SQLException {
        String sql = "DELETE FROM Endereco WHERE enderecoPK = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enderecoPK);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um endereço pelo ID
    public Endereco buscarEnderecoPorId(int enderecoPK) throws SQLException {
        String sql = "SELECT * FROM Endereco WHERE enderecoPK = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enderecoPK);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setEnderecoPK(rs.getInt("enderecoPK"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setBairro(rs.getString("bairro"));
                    return endereco;
                }
            }
        }
        return null; // Se não encontrar
    }

    // Método para listar todos os endereços
    public List<Endereco> listarEnderecos() throws SQLException {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM Endereco";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setEnderecoPK(rs.getInt("enderecoPK"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                lista.add(endereco);
            }
        }
        return lista;
    }
}

