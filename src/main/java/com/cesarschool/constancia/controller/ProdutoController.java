package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.ProdutoDAO;
import com.cesarschool.constancia.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoDAO produtoDAO;

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoDAO.findAll();
    }

    // Buscar um produto pelo código
    @GetMapping("/{codigo}")
    public Produto buscarProdutoPorCodigo(@PathVariable int codigo) {
        return produtoDAO.findByCodigo(codigo);
    }

    // Criar um novo produto
    @PostMapping
    public String salvarProduto(@RequestBody Produto produto) {
        produtoDAO.salvarProduto(produto);
        return "Produto salvo com sucesso!";
    }

    // Atualizar um produto existente
    @PutMapping("/{codigo}")
    public String atualizarProduto(@PathVariable int codigo, @RequestBody Produto produto) {
        produto.setCodigo(codigo); // Garante que o código da URL seja usado
        produtoDAO.editarProduto(produto);
        return "Produto atualizado com sucesso!";
    }

    // Deletar um produto pelo código
    @DeleteMapping("/{codigo}")
    public String deletarProduto(@PathVariable int codigo) {
        produtoDAO.deletarProduto(codigo);
        return "Produto deletado com sucesso!";
    }
}

