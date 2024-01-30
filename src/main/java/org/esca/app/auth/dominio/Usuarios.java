package org.esca.app.auth.dominio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    private String phone;
    private String cargo;
    private java.time.LocalDate created_at;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;
}
