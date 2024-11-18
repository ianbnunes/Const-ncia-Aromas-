package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Cliente> rowMapper = (ResultSet rs, int rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setCpf(rs.getString("cpf"));
        cliente.setNome(rs.getString("nome"));
        cliente.setTelefoneId(rs.getInt("fk_telefone_telefone_PK"));
        cliente.setEmail(rs.getString("email"));
        cliente.setSenha(rs.getString("senha"));
        cliente.setEnderecoId(rs.getInt("fk_endereco_endereco_PK"));
        return cliente;
    };

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM Cliente";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, cpf);
    }

    public void salvarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf, nome, fk_telefone_telefone_PK, email, senha, fk_endereco_endereco_PK) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getCpf(), cliente.getNome(), cliente.getTelefoneId(),
                cliente.getEmail(), cliente.getSenha(), cliente.getEnderecoId());
    }

    public void editarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, fk_telefone_telefone_PK = ?, email = ?, senha = ?, fk_endereco_endereco_PK = ? " +
                "WHERE cpf = ?";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getTelefoneId(), cliente.getEmail(),
                cliente.getSenha(), cliente.getEnderecoId(), cliente.getCpf());
    }

    public void deletarCliente(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
