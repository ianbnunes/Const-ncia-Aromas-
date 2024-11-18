package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.CarrinhoDAO;
import com.cesarschool.constancia.model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoDAO carrinhoDAO; // Certifique-se de que CarrinhoDAO está anotado com @Repository

    // Listar todos os carrinhos
    @GetMapping
    public List<Carrinho> listarCarrinhos() {
        try {
            return carrinhoDAO.listarCarrinhos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar carrinhos: " + e.getMessage());
        }
    }

    // Buscar um carrinho pelo ID
    @GetMapping("/{idCarrinho}")
    public Carrinho buscarCarrinhoPorId(@PathVariable int idCarrinho) {
        try {
            return carrinhoDAO.buscarCarrinhoPorId(idCarrinho);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar carrinho: " + e.getMessage());
        }
    }

    // Criar um novo carrinho
    @PostMapping
    public String criarCarrinho(@RequestBody Carrinho carrinho) {
        try {
            carrinhoDAO.inserirCarrinho(carrinho);
            return "Carrinho criado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao criar carrinho: " + e.getMessage();
        }
    }

    // Atualizar um carrinho existente
    @PutMapping("/{idCarrinho}")
    public String atualizarCarrinho(@PathVariable int idCarrinho, @RequestBody Carrinho carrinho) {
        try {
            carrinho.setIdCarrinho(idCarrinho); // Garante que o ID da URL seja usado
            carrinhoDAO.atualizarCarrinho(carrinho);
            return "Carrinho atualizado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao atualizar carrinho: " + e.getMessage();
        }
    }

    // Deletar um carrinho pelo ID
    @DeleteMapping("/{idCarrinho}")
    public String deletarCarrinho(@PathVariable int idCarrinho) {
        try {
            carrinhoDAO.excluirCarrinho(idCarrinho);
            return "Carrinho deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao deletar carrinho: " + e.getMessage();
        }
    }
}

