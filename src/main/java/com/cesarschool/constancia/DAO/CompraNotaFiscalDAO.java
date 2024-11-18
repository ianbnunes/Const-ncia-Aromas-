package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.CompraNotaFiscal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompraNotaFiscalDAO {

    private final Connection connection;

    // Construtor para inicializar a conexão
    public CompraNotaFiscalDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir uma compra/nota fiscal
    public void inserirCompraNotaFiscal(CompraNotaFiscal compraNotaFiscal) throws SQLException {
        String sql = "INSERT INTO CompraNotaFiscal (numero, data, valor, status, nota, descItens, cpfCnpjComprador, dataEmissao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, compraNotaFiscal.getNumero());
            stmt.setDate(2, new java.sql.Date(compraNotaFiscal.getData().getTime()));
            stmt.setDouble(3, compraNotaFiscal.getValor());
            stmt.setString(4, compraNotaFiscal.getStatus());
            stmt.setString(5, compraNotaFiscal.getNota());
            stmt.setString(6, compraNotaFiscal.getDescItens());
            stmt.setString(7, compraNotaFiscal.getCpfCnpjComprador());
            stmt.setDate(8, new java.sql.Date(compraNotaFiscal.getDataEmissao().getTime()));
            stmt.executeUpdate();
        }
    }

    // Método para atualizar uma compra/nota fiscal
    public void atualizarCompraNotaFiscal(CompraNotaFiscal compraNotaFiscal) throws SQLException {
        String sql = "UPDATE CompraNotaFiscal SET data = ?, valor = ?, status = ?, nota = ?, descItens = ?, " +
                "cpfCnpjComprador = ?, dataEmissao = ? WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(compraNotaFiscal.getData().getTime()));
            stmt.setDouble(2, compraNotaFiscal.getValor());
            stmt.setString(3, compraNotaFiscal.getStatus());
            stmt.setString(4, compraNotaFiscal.getNota());
            stmt.setString(5, compraNotaFiscal.getDescItens());
            stmt.setString(6, compraNotaFiscal.getCpfCnpjComprador());
            stmt.setDate(7, new java.sql.Date(compraNotaFiscal.getDataEmissao().getTime()));
            stmt.setInt(8, compraNotaFiscal.getNumero());
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma compra/nota fiscal pelo número
    public void excluirCompraNotaFiscal(int numero) throws SQLException {
        String sql = "DELETE FROM CompraNotaFiscal WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        }
    }

    // Método para buscar uma compra/nota fiscal pelo número
    public CompraNotaFiscal buscarCompraNotaFiscalPorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM CompraNotaFiscal WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CompraNotaFiscal compraNotaFiscal = new CompraNotaFiscal();
                    compraNotaFiscal.setNumero(rs.getInt("numero"));
                    compraNotaFiscal.setData(rs.getDate("data"));
                    compraNotaFiscal.setValor(rs.getDouble("valor"));
                    compraNotaFiscal.setStatus(rs.getString("status"));
                    compraNotaFiscal.setNota(rs.getString("nota"));
                    compraNotaFiscal.setDescItens(rs.getString("descItens"));
                    compraNotaFiscal.setCpfCnpjComprador(rs.getString("cpfCnpjComprador"));
                    compraNotaFiscal.setDataEmissao(rs.getDate("dataEmissao"));
                    return compraNotaFiscal;
                }
            }
        }
        return null; // Se não encontrar
    }

    // Método para listar todas as compras/notas fiscais
    public List<CompraNotaFiscal> listarComprasNotasFiscais() throws SQLException {
        List<CompraNotaFiscal> lista = new ArrayList<>();
        String sql = "SELECT * FROM CompraNotaFiscal";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CompraNotaFiscal compraNotaFiscal = new CompraNotaFiscal();
                compraNotaFiscal.setNumero(rs.getInt("numero"));
                compraNotaFiscal.setData(rs.getDate("data"));
                compraNotaFiscal.setValor(rs.getDouble("valor"));
                compraNotaFiscal.setStatus(rs.getString("status"));
                compraNotaFiscal.setNota(rs.getString("nota"));
                compraNotaFiscal.setDescItens(rs.getString("descItens"));
                compraNotaFiscal.setCpfCnpjComprador(rs.getString("cpfCnpjComprador"));
                compraNotaFiscal.setDataEmissao(rs.getDate("dataEmissao"));
                lista.add(compraNotaFiscal);
            }
        }
        return lista;
    }
}

