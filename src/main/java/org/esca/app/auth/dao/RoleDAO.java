package org.esca.app.auth.dao;

import org.esca.app.auth.dominio.Roles;

import java.util.List;

public interface RoleDAO {
    public Roles getById(final Long id);
    public void addRole(Roles role);
    public void updateRole(Roles role);
    public void deleteRole(Roles role);
    public List<Roles> selectRole();

    public void comboBoxRole();
}
