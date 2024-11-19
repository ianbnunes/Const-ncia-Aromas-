package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Avalia {
    private String clienteCpf; // Relacionamento com Cliente
    private int nota;
    private int compraNumero;  // Relacionamento com CompraNotaFiscal
}