package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Supervisiona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class SupervisionaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Supervisiona> rowMapper = (ResultSet rs, int rowNum) -> {
        Supervisiona supervisiona = new Supervisiona();
        supervisiona.setFuncionarioCpf(rs.getString("fk_Funcionario_cpf"));
        return supervisiona;
    };

    public List<Supervisiona> findAll() {
        String sql = "SELECT * FROM Supervisiona";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void salvarSupervisiona(String funcionarioCpf) {
        String sql = "INSERT INTO Supervisiona (fk_Funcionario_cpf) VALUES (?)";
        jdbcTemplate.update(sql, funcionarioCpf);
    }

    public void deletarSupervisiona(String funcionarioCpf) {
        String sql = "DELETE FROM Supervisiona WHERE fk_Funcionario_cpf = ?";
        jdbcTemplate.update(sql, funcionarioCpf);
    }
}
