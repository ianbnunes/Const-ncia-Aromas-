package com.cesarschool.constancia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Endereco {
    private int enderecoPK;
    private String numero;
    private String rua;
    private String bairro;
}
