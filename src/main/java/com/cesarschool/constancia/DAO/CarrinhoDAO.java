package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Carrinho;
import com.cesarschool.constancia.config.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarrinhoDAO {

    private Connection connection;

    public CarrinhoDAO() {
        try {
            // Usando a classe DatabaseConnection para obter a conexão
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }

    // Método para inserir um carrinho
    public void inserirCarrinho(Carrinho carrinho) throws SQLException {
        String sql = "INSERT INTO Carrinho (idCarrinho, qtdProdutos, dataAlteracao, dataCriacao, clienteCpf, produtoCodigo) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carrinho.getIdCarrinho());
            stmt.setInt(2, carrinho.getQtdProdutos());
            stmt.setDate(3, new java.sql.Date(carrinho.getDataAlteracao().getTime()));
            stmt.setDate(4, new java.sql.Date(carrinho.getDataCriacao().getTime()));
            stmt.setString(5, carrinho.getClienteCpf());
            stmt.setInt(6, carrinho.getProdutoCodigo());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um carrinho
    public void atualizarCarrinho(Carrinho carrinho) throws SQLException {
        String sql = "UPDATE Carrinho SET qtdProdutos = ?, dataAlteracao = ?, clienteCpf = ?, produtoCodigo = ? " +
                "WHERE idCarrinho = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carrinho.getQtdProdutos());
            stmt.setDate(2, new java.sql.Date(carrinho.getDataAlteracao().getTime()));
            stmt.setString(3, carrinho.getClienteCpf());
            stmt.setInt(4, carrinho.getProdutoCodigo());
            stmt.setInt(5, carrinho.getIdCarrinho());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um carrinho pelo ID
    public void excluirCarrinho(int idCarrinho) throws SQLException {
        String sql = "DELETE FROM Carrinho WHERE idCarrinho = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCarrinho);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um carrinho pelo ID
    public Carrinho buscarCarrinhoPorId(int idCarrinho) throws SQLException {
        String sql = "SELECT * FROM Carrinho WHERE idCarrinho = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCarrinho);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Carrinho carrinho = new Carrinho();
                    carrinho.setIdCarrinho(rs.getInt("idCarrinho"));
                    carrinho.setQtdProdutos(rs.getInt("qtdProdutos"));
                    carrinho.setDataAlteracao(rs.getDate("dataAlteracao"));
                    carrinho.setDataCriacao(rs.getDate("dataCriacao"));
                    carrinho.setClienteCpf(rs.getString("clienteCpf"));
                    carrinho.setProdutoCodigo(rs.getInt("produtoCodigo"));
                    return carrinho;
                }
            }
        }
        return null; // Se não encontrar
    }

    // Método para listar todos os carrinhos
    public List<Carrinho> listarCarrinhos() throws SQLException {
        List<Carrinho> carrinhos = new ArrayList<>();
        String sql = "SELECT * FROM Carrinho";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Carrinho carrinho = new Carrinho();
                carrinho.setIdCarrinho(rs.getInt("idCarrinho"));
                carrinho.setQtdProdutos(rs.getInt("qtdProdutos"));
                carrinho.setDataAlteracao(rs.getDate("dataAlteracao"));
                carrinho.setDataCriacao(rs.getDate("dataCriacao"));
                carrinho.setClienteCpf(rs.getString("clienteCpf"));
                carrinho.setProdutoCodigo(rs.getInt("produtoCodigo"));
                carrinhos.add(carrinho);
            }
        }
        return carrinhos;
    }
}
