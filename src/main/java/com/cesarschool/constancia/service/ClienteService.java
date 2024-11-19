package com.cesarschool.constancia.service;

import com.cesarschool.constancia.DAO.ClienteDAO;
import com.cesarschool.constancia.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Lista todos os clientes
    public List<Cliente> listarClientes() {
        return clienteDAO.findAll();
    }

    // Busca um cliente pelo CPF
    public Cliente buscarClientePorCpf(String cpf) {
        Cliente cliente = clienteDAO.findByCpf(cpf);
        if (cliente == null) {
            throw new RuntimeException("Cliente com CPF " + cpf + " não encontrado.");
        }
        return cliente;
    }

    // Salva um novo cliente
    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        clienteDAO.salvarCliente(cliente);
    }

    // Atualiza um cliente existente
    public void atualizarCliente(String cpf, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteDAO.findByCpf(cpf);
        if (clienteExistente == null) {
            throw new RuntimeException("Cliente com CPF " + cpf + " não encontrado.");
        }
        clienteAtualizado.setCpf(cpf); // Garante que o CPF não será alterado
        validarCliente(clienteAtualizado);
        clienteDAO.editarCliente(clienteAtualizado);
    }

    // Deleta um cliente pelo CPF
    public void deletarCliente(String cpf) {
        Cliente clienteExistente = clienteDAO.findByCpf(cpf);
        if (clienteExistente == null) {
            throw new RuntimeException("Cliente com CPF " + cpf + " não encontrado.");
        }
        clienteDAO.deletarCliente(cpf);
    }

    // Validação básica do cliente
    private void validarCliente(Cliente cliente) {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (cliente.getTelefoneId() == 0) {
            throw new IllegalArgumentException("O ID do telefone é obrigatório.");
        }
        if (cliente.getEnderecoId() == 0) {
            throw new IllegalArgumentException("O ID do endereço é obrigatório.");
        }
    }
}
