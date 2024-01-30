package org.esca.app.cadastros.dominio;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Students {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    private String nacionalidade;
    private String naturalidade;
    private String naturalidade_uf;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dtaNascimento;
    @Column(name = "mae", length = 80, nullable = false)
    private String nomeMae;
    @Column(name = "pai", length = 80)
    private String nomePai;
    @Column(name = "responsavel_academico", length = 80, nullable = false)
    private String responsavelAcademico;
    @Column(name = "responsavel_financeiro", length = 80)
    private String responsavelFinanceiro;
    private String sexo;
    private boolean status;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    @Column(length = 11)
    private String telefone;
}
