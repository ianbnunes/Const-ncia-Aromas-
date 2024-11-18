package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Cupom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupomDAO {

    private final Connection connection;

    // Construtor para inicializar a conexão
    public CupomDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um cupom
    public void inserirCupom(Cupom cupom) throws SQLException {
        String sql = "INSERT INTO Cupom (codigo, descricao, valorDesconto, dataValidade, condUso) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cupom.getCodigo());
            stmt.setString(2, cupom.getDescricao());
            stmt.setDouble(3, cupom.getValorDesconto());
            stmt.setDate(4, new java.sql.Date(cupom.getDataValidade().getTime()));
            stmt.setString(5, cupom.getCondUso());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um cupom
    public void atualizarCupom(Cupom cupom) throws SQLException {
        String sql = "UPDATE Cupom SET descricao = ?, valorDesconto = ?, dataValidade = ?, condUso = ? " +
                "WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cupom.getDescricao());
            stmt.setDouble(2, cupom.getValorDesconto());
            stmt.setDate(3, new java.sql.Date(cupom.getDataValidade().getTime()));
            stmt.setString(4, cupom.getCondUso());
            stmt.setInt(5, cupom.getCodigo());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um cupom pelo código
    public void excluirCupom(int codigo) throws SQLException {
        String sql = "DELETE FROM Cupom WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um cupom pelo código
    public Cupom buscarCupomPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM Cupom WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cupom cupom = new Cupom();
                    cupom.setCodigo(rs.getInt("codigo"));
                    cupom.setDescricao(rs.getString("descricao"));
                    cupom.setValorDesconto(rs.getDouble("valorDesconto"));
                    cupom.setDataValidade(rs.getDate("dataValidade"));
                    cupom.setCondUso(rs.getString("condUso"));
                    return cupom;
                }
            }
        }
        return null; // Se não encontrar
    }

    // Método para listar todos os cupons
    public List<Cupom> listarCupons() throws SQLException {
        List<Cupom> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cupom";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cupom cupom = new Cupom();
                cupom.setCodigo(rs.getInt("codigo"));
                cupom.setDescricao(rs.getString("descricao"));
                cupom.setValorDesconto(rs.getDouble("valorDesconto"));
                cupom.setDataValidade(rs.getDate("dataValidade"));
                cupom.setCondUso(rs.getString("condUso"));
                lista.add(cupom);
            }
        }
        return lista;
    }
}
