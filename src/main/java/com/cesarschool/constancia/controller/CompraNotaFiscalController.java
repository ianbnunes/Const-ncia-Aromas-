package com.cesarschool.constancia.controller;

import com.cesarschool.constancia.DAO.CompraNotaFiscalDAO;
import com.cesarschool.constancia.model.CompraNotaFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/compras-notas-fiscais")
public class CompraNotaFiscalController {

    @Autowired
    private CompraNotaFiscalDAO compraNotaFiscalDAO;

    // Buscar todas as compras/notas fiscais
    @GetMapping
    public List<CompraNotaFiscal> listarComprasNotasFiscais() throws SQLException {
        return compraNotaFiscalDAO.listarComprasNotasFiscais();
    }

    // Buscar uma compra/nota fiscal pelo número
    @GetMapping("/{numero}")
    public CompraNotaFiscal buscarCompraNotaFiscalPorNumero(@PathVariable int numero) throws SQLException {
        return compraNotaFiscalDAO.buscarCompraNotaFiscalPorNumero(numero);
    }

    // Criar uma nova compra/nota fiscal
    @PostMapping
    public String criarCompraNotaFiscal(@RequestBody CompraNotaFiscal compraNotaFiscal) {
        try {
            compraNotaFiscalDAO.inserirCompraNotaFiscal(compraNotaFiscal);
            return "Compra/Nota Fiscal criada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao criar Compra/Nota Fiscal: " + e.getMessage();
        }
    }

    // Atualizar uma compra/nota fiscal existente
    @PutMapping("/{numero}")
    public String atualizarCompraNotaFiscal(@PathVariable int numero, @RequestBody CompraNotaFiscal compraNotaFiscal) {
        try {
            compraNotaFiscal.setNumero(numero); // Garante que o número da URL seja usado
            compraNotaFiscalDAO.atualizarCompraNotaFiscal(compraNotaFiscal);
            return "Compra/Nota Fiscal atualizada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao atualizar Compra/Nota Fiscal: " + e.getMessage();
        }
    }

    // Deletar uma compra/nota fiscal pelo número
    @DeleteMapping("/{numero}")
    public String deletarCompraNotaFiscal(@PathVariable int numero) {
        try {
            compraNotaFiscalDAO.excluirCompraNotaFiscal(numero);
            return "Compra/Nota Fiscal deletada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao deletar Compra/Nota Fiscal: " + e.getMessage();
        }
    }
}

