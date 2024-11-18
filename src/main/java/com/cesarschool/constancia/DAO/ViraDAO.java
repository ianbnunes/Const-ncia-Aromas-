package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Vira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class ViraDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Vira> rowMapper = (ResultSet rs, int rowNum) -> {
        Vira vira = new Vira();
        vira.setCompraNumero(rs.getInt("fk_Compra_numero"));
        vira.setIdCarrinho(rs.getInt("fk_idCarrinho"));
        vira.setData(rs.getDate("data"));
        return vira;
    };

    public List<Vira> findAll() {
        String sql = "SELECT * FROM Vira";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Vira findByIds(int compraNumero, int idCarrinho) {
        String sql = "SELECT * FROM Vira WHERE fk_Compra_numero = ? AND fk_idCarrinho = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, compraNumero, idCarrinho);
    }

    public void salvarVira(Vira vira) {
        String sql = "INSERT INTO Vira (fk_Compra_numero, fk_idCarrinho, data) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, vira.getCompraNumero(), vira.getIdCarrinho(), Date.valueOf(vira.getData().toString()));
    }

    public void deletarVira(int compraNumero, int idCarrinho) {
        String sql = "DELETE FROM Vira WHERE fk_Compra_numero = ? AND fk_idCarrinho = ?";
        jdbcTemplate.update(sql, compraNumero, idCarrinho);
    }

    public void atualizarData(int compraNumero, int idCarrinho, Date novaData) {
        String sql = "UPDATE Vira SET data = ? WHERE fk_Compra_numero = ? AND fk_idCarrinho = ?";
        jdbcTemplate.update(sql, novaData, compraNumero, idCarrinho);
    }
}
