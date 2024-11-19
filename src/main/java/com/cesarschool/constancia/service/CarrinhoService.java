package com.cesarschool.constancia.service;

import com.cesarschool.constancia.DAO.CarrinhoDAO;
import com.cesarschool.constancia.model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoDAO carrinhoDAO;

    // Listar todos os carrinhos
    public List<Carrinho> listarCarrinhos() throws SQLException {
        return carrinhoDAO.listarCarrinhos();
    }

    // Buscar um carrinho pelo ID
    public Carrinho buscarCarrinhoPorId(int idCarrinho) throws SQLException {
        Carrinho carrinho = carrinhoDAO.buscarCarrinhoPorId(idCarrinho);
        if (carrinho == null) {
            throw new RuntimeException("Carrinho com ID " + idCarrinho + " não encontrado.");
        }
        return carrinho;
    }

    // Criar um novo carrinho
    public void criarCarrinho(Carrinho carrinho) throws SQLException {
        validarCarrinho(carrinho);
        carrinhoDAO.inserirCarrinho(carrinho);
    }

    // Atualizar um carrinho existente
    public void atualizarCarrinho(int idCarrinho, Carrinho carrinho) throws SQLException {
        Carrinho existente = carrinhoDAO.buscarCarrinhoPorId(idCarrinho);
        if (existente == null) {
            throw new RuntimeException("Carrinho com ID " + idCarrinho + " não encontrado para atualização.");
        }
        carrinho.setIdCarrinho(idCarrinho); // Garante que o ID da URL seja usado
        validarCarrinho(carrinho);
        carrinhoDAO.atualizarCarrinho(carrinho);
    }

    // Deletar um carrinho pelo ID
    public void deletarCarrinho(int idCarrinho) throws SQLException {
        Carrinho existente = carrinhoDAO.buscarCarrinhoPorId(idCarrinho);
        if (existente == null) {
            throw new RuntimeException("Carrinho com ID " + idCarrinho + " não encontrado para deleção.");
        }
        carrinhoDAO.excluirCarrinho(idCarrinho);
    }

    // Validação básica do carrinho (exemplo)
    private void validarCarrinho(Carrinho carrinho) {
        if (carrinho.getQtdProdutos() <= 0) {
            throw new IllegalArgumentException("A quantidade de produtos deve ser maior que zero.");
        }
        if (carrinho.getClienteCpf() == null || carrinho.getClienteCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (carrinho.getProdutoCodigo() <= 0) {
            throw new IllegalArgumentException("O código do produto é inválido.");
        }
    }
}
