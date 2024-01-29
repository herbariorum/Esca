package org.esca.app.cadastros.dominio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CodigoUf", nullable = false)
    private int codigoUf;
    @Column(name = "Nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "Uf", length = 2, nullable = false)
    private String uf;
    @Column(name = "Regiao", nullable = false)
    private int região;

    @Override
    public String toString() {
        return this.getUf();
    }
}
