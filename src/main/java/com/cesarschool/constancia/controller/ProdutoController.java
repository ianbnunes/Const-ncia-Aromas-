package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.ProdutoDAO;
import com.cesarschool.constancia.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "http://localhost:3001")
public class ProdutoController {

    @Autowired
    private ProdutoDAO produtoDAO;

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        try {
            List<Produto> produtos = produtoDAO.findAll();
            if (produtos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Buscar um produto pelo código
    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProdutoPorCodigo(@PathVariable int codigo) {
        try {
            Produto produto = produtoDAO.findByCodigo(codigo);
            if (produto != null) {
                return ResponseEntity.ok(produto);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Criar um novo produto
    @PostMapping
    public ResponseEntity<String> salvarProduto(@RequestBody Produto produto) {
        try {
            produtoDAO.salvarProduto(produto);
            return ResponseEntity.ok("Produto salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao salvar o produto: " + e.getMessage());
        }
    }

    // Atualizar um produto existente
    @PutMapping("/{codigo}")
    public ResponseEntity<String> atualizarProduto(@PathVariable int codigo, @RequestBody Produto produto) {
        try {
            Produto produtoExistente = produtoDAO.findByCodigo(codigo);
            if (produtoExistente == null) {
                return ResponseEntity.notFound().build();
            }
            produto.setCodigo(codigo); // Garante que o código da URL seja usado
            produtoDAO.editarProduto(produto);
            return ResponseEntity.ok("Produto atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    // Deletar um produto pelo código
    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> deletarProduto(@PathVariable int codigo) {
        try {
            Produto produtoExistente = produtoDAO.findByCodigo(codigo);
            if (produtoExistente == null) {
                return ResponseEntity.notFound().build();
            }
            produtoDAO.deletarProduto(codigo);
            return ResponseEntity.ok("Produto deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao deletar o produto: " + e.getMessage());
        }
    }
}
