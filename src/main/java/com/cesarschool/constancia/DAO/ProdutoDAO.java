package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProdutoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para mapear os resultados do banco para o objeto Produto
    private final RowMapper<Produto> rowMapper = (ResultSet rs, int rowNum) -> {
        Produto produto = new Produto();
        produto.setCodigo(rs.getInt("codigo"));
        produto.setCategoria(rs.getString("categoria"));
        produto.setMarca(rs.getString("marca"));
        produto.setNcm(rs.getString("ncm"));
        produto.setCfop(rs.getString("cfop"));
        produto.setEstoque(rs.getInt("estoque"));
        produto.setProdutoTipo(rs.getInt("produtoTipo"));
        produto.setPreco(rs.getBigDecimal("preco"));
        return produto;
    };


    public List<Produto> findAll() {
        String sql = "SELECT * FROM Produto";
        return jdbcTemplate.query(sql, rowMapper);
    }


    public Produto findByCodigo(int codigo) {
        String sql = "SELECT * FROM Produto WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, codigo);
    }


    public void salvarProduto(Produto produto) {
        String sql = "INSERT INTO Produto (categoria, marca, ncm, cfop, estoque, produtoTipo, preco) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getCategoria(), produto.getMarca(), produto.getNcm(),
                produto.getCfop(), produto.getEstoque(), produto.getProdutoTipo(), produto.getPreco());
    }


    public void editarProduto(Produto produto) {
        String sql = "UPDATE Produto SET categoria = ?, marca = ?, ncm = ?, cfop = ?, estoque = ?, produtoTipo = ?, preco = ? " +
                "WHERE codigo = ?";
        jdbcTemplate.update(sql, produto.getCategoria(), produto.getMarca(), produto.getNcm(),
                produto.getCfop(), produto.getEstoque(), produto.getProdutoTipo(),
                produto.getPreco(), produto.getCodigo());
    }


    public void deletarProduto(int codigo) {
        String sql = "DELETE FROM Produto WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }
}
