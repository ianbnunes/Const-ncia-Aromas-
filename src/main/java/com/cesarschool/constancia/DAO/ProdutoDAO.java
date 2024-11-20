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
        produto.setNcm(rs.getString("NCM"));
        produto.setCfop(rs.getString("CFOP"));
        produto.setEstoque(rs.getInt("estoque"));
        produto.setProdutoTipo(rs.getInt("Produto_TIPO"));
        produto.setPreco(rs.getBigDecimal("preco"));
        return produto;
    };

    public List<Produto> findAll() {
        String sql = "SELECT * FROM produto"; // Corrigido para "produto" (nome da tabela no banco)
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Produto findByCodigo(int codigo) {
        String sql = "SELECT * FROM produto WHERE codigo = ?"; // Corrigido para "produto"
        return jdbcTemplate.queryForObject(sql, rowMapper, codigo);
    }

    public void salvarProduto(Produto produto) {
        String sql = "INSERT INTO produto (categoria, marca, NCM, CFOP, estoque, Produto_TIPO, preco) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"; // Corrigido os nomes das colunas para coincidir com a tabela
        jdbcTemplate.update(sql,
                produto.getCategoria(),
                produto.getMarca(),
                produto.getNcm(),
                produto.getCfop(),
                produto.getEstoque(),
                produto.getProdutoTipo(),
                produto.getPreco());
    }

    public void editarProduto(Produto produto) {
        String sql = "UPDATE produto SET categoria = ?, marca = ?, NCM = ?, CFOP = ?, estoque = ?, Produto_TIPO = ?, preco = ? " +
                "WHERE codigo = ?"; // Corrigido para "produto"
        jdbcTemplate.update(sql,
                produto.getCategoria(),
                produto.getMarca(),
                produto.getNcm(),
                produto.getCfop(),
                produto.getEstoque(),
                produto.getProdutoTipo(),
                produto.getPreco(),
                produto.getCodigo());
    }

    public void deletarProduto(int codigo) {
        String sql = "DELETE FROM produto WHERE codigo = ?"; // Corrigido para "produto"
        jdbcTemplate.update(sql, codigo);
    }
}

