package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Funcionario> rowMapper = (ResultSet rs, int rowNum) -> {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCargo(rs.getString("cargo"));
        funcionario.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        funcionario.setCep(rs.getString("cep"));
        funcionario.setBairro(rs.getString("bairro"));
        funcionario.setRua(rs.getString("rua"));
        funcionario.setNumero(rs.getString("numero"));
        funcionario.setComplemento(rs.getString("complemento"));
        funcionario.setSupervisorCpf(rs.getString("supervisor_cpf"));
        return funcionario;
    };

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM Funcionario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Funcionario findByCpf(String cpf) {
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, cpf);
    }

    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (cpf, nome, cargo, idade, cep, bairro, rua, numero, complemento, supervisor_cpf) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, funcionario.getCpf(), funcionario.getNome(), funcionario.getCargo(),
                funcionario.getDataNasc(), funcionario.getCep(), funcionario.getBairro(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getComplemento(),
                funcionario.getSupervisorCpf());
    }

    public void editarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ?, data_nasc = ?, cep = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, supervisor_cpf = ? " +
                "WHERE cpf = ?";
        jdbcTemplate.update(sql, funcionario.getNome(), funcionario.getCargo(), funcionario.getDataNasc(),
                funcionario.getCep(), funcionario.getBairro(), funcionario.getRua(),
                funcionario.getNumero(), funcionario.getComplemento(), funcionario.getSupervisorCpf(),
                funcionario.getCpf());
    }

    public void deletarFuncionario(String cpf) {
        String sql = "DELETE FROM Funcionario WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
