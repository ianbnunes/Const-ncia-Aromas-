package com.cesarschool.constancia.DAO;

import com.cesarschool.constancia.model.Favorita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class FavoritaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeamento de resultados do banco de dados para o objeto Favorita
    private final RowMapper<Favorita> rowMapper = (ResultSet rs, int rowNum) -> {
        Favorita favorita = new Favorita();
        favorita.setClienteCpf(rs.getString("fk_Cliente_cpf"));
        favorita.setProdutoCodigo(rs.getInt("fk_Produto_codigo"));
        return favorita;
    };

    // Recupera todos os produtos favoritos de um cliente
    public List<Favorita> findByClienteCpf(String clienteCpf) {
        String sql = "SELECT * FROM Favorita WHERE fk_Cliente_cpf = ?";
        return jdbcTemplate.query(sql, rowMapper, clienteCpf);
    }

    // Recupera todos os registros da tabela Favorita
    public List<Favorita> findAll() {
        String sql = "SELECT * FROM Favorita";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Adiciona um produto aos favoritos de um cliente
    public void salvarFavorita(String clienteCpf, int produtoCodigo) {
        String sql = "INSERT INTO Favorita (fk_Cliente_cpf, fk_Produto_codigo) VALUES (?, ?)";
        jdbcTemplate.update(sql, clienteCpf, produtoCodigo);
    }

    // Remove um produto dos favoritos de um cliente
    public void deletarFavorita(String clienteCpf, int produtoCodigo) {
        String sql = "DELETE FROM Favorita WHERE fk_Cliente_cpf = ? AND fk_Produto_codigo = ?";
        jdbcTemplate.update(sql, clienteCpf, produtoCodigo);
    }
}
