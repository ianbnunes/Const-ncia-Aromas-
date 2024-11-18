package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Avalia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliaDAO {

    // Inserir um registro em Avalia
    public void inserirAvalia(Avalia avalia) {
        String sql = "INSERT INTO Avalia (fk_Cliente_cpf, fk_Compra_numero) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, avalia.getClienteCpf());
            stmt.setInt(2, avalia.getCompraNumero());

            stmt.executeUpdate();
            System.out.println("Avaliação inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os registros de Avalia
    public List<Avalia> listarAvaliacoes() {
        String sql = "SELECT fk_Cliente_cpf, fk_Compra_numero FROM Avalia";
        List<Avalia> avaliacoes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Avalia avalia = new Avalia();
                avalia.setClienteCpf(rs.getString("fk_Cliente_cpf"));
                avalia.setCompraNumero(rs.getInt("fk_Compra_numero"));
                avaliacoes.add(avalia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avaliacoes;
    }

    // Deletar uma avaliação específica
    public void deletarAvalia(String clienteCpf, int compraNumero) {
        String sql = "DELETE FROM Avalia WHERE fk_Cliente_cpf = ? AND fk_Compra_numero = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clienteCpf);
            stmt.setInt(2, compraNumero);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Avaliação deletada com sucesso!");
            } else {
                System.out.println("Nenhuma avaliação encontrada para deletar.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

