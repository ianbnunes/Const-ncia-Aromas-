package com.cesarschool.constancia.service;

import com.cesarschool.constancia.DAO.ProdutoDAO;
import com.cesarschool.constancia.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    @Autowired
    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    // Lista todos os produtos
    public List<Produto> listarProdutos() {
        return produtoDAO.findAll();
    }

    // Busca um produto pelo código
    public Produto buscarProdutoPorCodigo(int codigo) {
        Produto produto = produtoDAO.findByCodigo(codigo);
        if (produto == null) {
            throw new RuntimeException("Produto com código " + codigo + " não encontrado.");
        }
        return produto;
    }

    // Salva um novo produto
    public void salvarProduto(Produto produto) {
        validarProduto(produto);
        produtoDAO.salvarProduto(produto);
    }

    // Atualiza um produto existente
    public void atualizarProduto(int codigo, Produto produtoAtualizado) {
        Produto produtoExistente = produtoDAO.findByCodigo(codigo);
        if (produtoExistente == null) {
            throw new RuntimeException("Produto com código " + codigo + " não encontrado.");
        }
        produtoAtualizado.setCodigo(codigo); // Garante que o código não será alterado
        validarProduto(produtoAtualizado);
        produtoDAO.editarProduto(produtoAtualizado);
    }

    // Deleta um produto pelo código
    public void deletarProduto(int codigo) {
        Produto produtoExistente = produtoDAO.findByCodigo(codigo);
        if (produtoExistente == null) {
            throw new RuntimeException("Produto com código " + codigo + " não encontrado.");
        }
        produtoDAO.deletarProduto(codigo);
    }

    // Validação básica do produto
    private void validarProduto(Produto produto) {
        if (produto.getCodigo() <= 0) {
            throw new IllegalArgumentException("O código do produto deve ser maior que zero.");
        }
        if (produto.getCategoria() == null || produto.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("A categoria do produto é obrigatória.");
        }
        if (produto.getMarca() == null || produto.getMarca().isEmpty()) {
            throw new IllegalArgumentException("A marca do produto é obrigatória.");
        }
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        if (produto.getEstoque() < 0) {
            throw new IllegalArgumentException("O estoque do produto não pode ser negativo.");
        }
        if (produto.getProdutoTipo() <= 0) {
            throw new IllegalArgumentException("O tipo do produto deve ser maior que zero.");
        }
        if (produto.getNcm() == null || produto.getNcm().isEmpty()) {
            throw new IllegalArgumentException("O NCM do produto é obrigatório.");
        }
        if (produto.getCfop() == null || produto.getCfop().isEmpty()) {
            throw new IllegalArgumentException("O CFOP do produto é obrigatório.");
        }
    }
}
