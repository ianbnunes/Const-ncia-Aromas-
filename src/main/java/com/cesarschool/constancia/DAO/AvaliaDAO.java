package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Avalia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliaDAO {

    private final Connection connection;

    // Construtor para inicializar a conexão
    public AvaliaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir uma avaliação
    public void inserirAvalia(Avalia avalia) throws SQLException {
        String sql = "INSERT INTO avalia (fk_Cliente_cpf, fk_Compra_numero, nota) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, avalia.getClienteCpf());
            stmt.setInt(2, avalia.getCompraNumero());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar uma avaliação
    public void atualizarAvalia(Avalia avalia) throws SQLException {
        String sql = "UPDATE avalia SET fk_Compra_numero = ? WHERE fk_Cliente_cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, avalia.getCompraNumero());
            stmt.setString(2, avalia.getClienteCpf());
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma avaliação pelo CPF do cliente
    public void excluirAvalia(String clienteCpf) throws SQLException {
        String sql = "DELETE FROM avalia WHERE fk_Cliente_cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clienteCpf);
            stmt.executeUpdate();
        }
    }

    // Método para buscar uma avaliação pelo CPF do cliente
    public Avalia buscarAvaliaPorCpf(String clienteCpf) throws SQLException {
        String sql = "SELECT * FROM avalia WHERE fk_Cliente_cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clienteCpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Avalia avalia = new Avalia();
                    avalia.setClienteCpf(rs.getString("fk_Cliente_cpf"));
                    avalia.setCompraNumero(rs.getInt("fk_Compra_numero"));
                    return avalia;
                }
            }
        }
        return null; // Se não encontrar
    }

    // Método para listar todas as avaliações
    public List<Avalia> listarAvaliacoes() throws SQLException {
        List<Avalia> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM avalia";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Avalia avalia = new Avalia();
                avalia.setClienteCpf(rs.getString("fk_Cliente_cpf"));
                avalia.setCompraNumero(rs.getInt("fk_Compra_numero"));
                avaliacoes.add(avalia);
            }
        }
        return avaliacoes;
    }
}
