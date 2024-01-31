package org.esca.app.cadastros.dominio;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dta_nasc;
    private String cargo;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    @Column(length = 11)
    private String telefone;

    @OneToOne
    @JoinColumn(name="conta_bancaria_id", referencedColumnName = "id")
    private Banco conta_bancaria;

}
