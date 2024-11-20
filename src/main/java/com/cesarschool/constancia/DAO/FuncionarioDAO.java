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

    // Mapeamento de linhas para objetos Funcionario
    private final RowMapper<Funcionario> rowMapper = (ResultSet rs, int rowNum) -> {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCargo(rs.getString("cargo"));
        funcionario.setCep(rs.getString("cep"));
        funcionario.setBairro(rs.getString("bairro"));
        funcionario.setRua(rs.getString("rua"));
        funcionario.setNumero(rs.getString("numero"));
        funcionario.setComplemento(rs.getString("complemento"));
        funcionario.setSupervisorCpf(rs.getString("supervisor_cpf"));
        funcionario.setIdade(rs.getString("idade")); // Ajustado para VARCHAR(100)
        return funcionario;
    };

    // Lista todos os funcionários
    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Busca um funcionário pelo CPF
    public Funcionario findByCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, cpf);
    }

    // Salva um novo funcionário
    public void salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf, nome, cargo, cep, bairro, rua, numero, complemento, supervisor_cpf, idade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                funcionario.getCpf(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getCep(),
                funcionario.getBairro(),
                funcionario.getRua(),
                funcionario.getNumero(),
                funcionario.getComplemento(),
                funcionario.getSupervisorCpf(),
                funcionario.getIdade()); // Ajustado para VARCHAR(100)
    }

    // Atualiza um funcionário existente
    public void editarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, cep = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, supervisor_cpf = ?, idade = ? " +
                "WHERE cpf = ?";
        jdbcTemplate.update(sql,
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getCep(),
                funcionario.getBairro(),
                funcionario.getRua(),
                funcionario.getNumero(),
                funcionario.getComplemento(),
                funcionario.getSupervisorCpf(),
                funcionario.getIdade(), // Ajustado para VARCHAR(100)
                funcionario.getCpf());
    }

    // Deleta um funcionário pelo CPF
    public void deletarFuncionario(String cpf) {
        String sql = "DELETE FROM funcionario WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
