package org.esca.app.auth.dao;

import org.esca.app.auth.dominio.Usuarios;

import java.util.List;

public interface UsuarioDAO {
    public Usuarios getById(final Long id);
    public void addUser(Usuarios usuarios);
    public void updateUser(Usuarios usuarios);
    public void deleteUser(Usuarios usuarios);
    public List<Usuarios> selectUser();
    public Usuarios loginUser(Usuarios usuarios);
    public Usuarios selectEmailUser(Usuarios usuarios);
    public  List<Usuarios> selectByValue(String value);
}
