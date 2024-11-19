package com.cesarschool.constancia.service;

import com.cesarschool.constancia.DAO.CompraNotaFiscalDAO;
import com.cesarschool.constancia.model.CompraNotaFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CompraNotaFiscalService {

    private final CompraNotaFiscalDAO compraNotaFiscalDAO;

    @Autowired
    public CompraNotaFiscalService(CompraNotaFiscalDAO compraNotaFiscalDAO) {
        this.compraNotaFiscalDAO = compraNotaFiscalDAO;
    }

    // Listar todas as compras/notas fiscais
    public List<CompraNotaFiscal> listarComprasNotasFiscais() throws SQLException {
        return compraNotaFiscalDAO.listarComprasNotasFiscais();
    }

    // Buscar uma compra/nota fiscal pelo número
    public CompraNotaFiscal buscarCompraNotaFiscalPorNumero(int numero) throws SQLException {
        CompraNotaFiscal compraNotaFiscal = compraNotaFiscalDAO.buscarCompraNotaFiscalPorNumero(numero);
        if (compraNotaFiscal == null) {
            throw new RuntimeException("Compra/Nota Fiscal com número " + numero + " não encontrada.");
        }
        return compraNotaFiscal;
    }

    // Inserir uma nova compra/nota fiscal
    public void criarCompraNotaFiscal(CompraNotaFiscal compraNotaFiscal) throws SQLException {
        validarCompraNotaFiscal(compraNotaFiscal);
        compraNotaFiscalDAO.inserirCompraNotaFiscal(compraNotaFiscal);
    }

    // Atualizar uma compra/nota fiscal existente
    public void atualizarCompraNotaFiscal(int numero, CompraNotaFiscal compraNotaFiscal) throws SQLException {
        CompraNotaFiscal existente = compraNotaFiscalDAO.buscarCompraNotaFiscalPorNumero(numero);
        if (existente == null) {
            throw new RuntimeException("Compra/Nota Fiscal com número " + numero + " não encontrada.");
        }
        compraNotaFiscal.setNumero(numero); // Garante que o número não será alterado
        validarCompraNotaFiscal(compraNotaFiscal);
        compraNotaFiscalDAO.atualizarCompraNotaFiscal(compraNotaFiscal);
    }

    // Deletar uma compra/nota fiscal pelo número
    public void deletarCompraNotaFiscal(int numero) throws SQLException {
        CompraNotaFiscal existente = compraNotaFiscalDAO.buscarCompraNotaFiscalPorNumero(numero);
        if (existente == null) {
            throw new RuntimeException("Compra/Nota Fiscal com número " + numero + " não encontrada.");
        }
        compraNotaFiscalDAO.excluirCompraNotaFiscal(numero);
    }

    // Validação de uma compra/nota fiscal
    private void validarCompraNotaFiscal(CompraNotaFiscal compraNotaFiscal) {
        if (compraNotaFiscal.getNumero() <= 0) {
            throw new IllegalArgumentException("O número da compra/nota fiscal deve ser maior que zero.");
        }
        if (compraNotaFiscal.getData() == null) {
            throw new IllegalArgumentException("A data da compra/nota fiscal é obrigatória.");
        }
        if (compraNotaFiscal.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da compra/nota fiscal deve ser maior que zero.");
        }
        if (compraNotaFiscal.getCpfCnpjComprador() == null || compraNotaFiscal.getCpfCnpjComprador().isEmpty()) {
            throw new IllegalArgumentException("O CPF/CNPJ do comprador é obrigatório.");
        }
    }
}
