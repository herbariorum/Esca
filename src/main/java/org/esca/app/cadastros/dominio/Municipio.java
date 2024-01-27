package org.esca.app.cadastros.dominio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Codigo", nullable = false)
    private int codigo;
    @Column(name = "Nome", length = 120, nullable = false)
    private String nome;
    @Column(name = "Uf", nullable = false)
    private String uf;

}
