package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class TelefoneDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Telefone> rowMapper = (ResultSet rs, int rowNum) -> {
        Telefone telefone = new Telefone();
        telefone.setTelefonePK(rs.getInt("telefone_PK"));
        telefone.setTelefone(rs.getString("telefone"));
        return telefone;
    };

    public List<Telefone> findAll() {
        String sql = "SELECT * FROM Telefone";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Telefone findById(int telefonePK) {
        String sql = "SELECT * FROM Telefone WHERE telefone_PK = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, telefonePK);
    }

    public void salvarTelefone(Telefone telefone) {
        String sql = "INSERT INTO Telefone (telefone) VALUES (?)";
        jdbcTemplate.update(sql, telefone.getTelefone());
    }

    public void editarTelefone(Telefone telefone) {
        String sql = "UPDATE Telefone SET telefone = ? WHERE telefone_PK = ?";
        jdbcTemplate.update(sql, telefone.getTelefone(), telefone.getTelefonePK());
    }

    public void deletarTelefone(int telefonePK) {
        String sql = "DELETE FROM Telefone WHERE telefone_PK = ?";
        jdbcTemplate.update(sql, telefonePK);
    }
}
