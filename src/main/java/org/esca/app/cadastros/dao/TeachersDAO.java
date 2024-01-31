package org.esca.app.cadastros.dao;

import org.esca.app.cadastros.dominio.Teachers;

import java.util.List;

public interface TeachersDAO {
    public Teachers getById(final Long id);
    public void addTeacher(Teachers teachers);
    public void updateTeacher(Teachers teachers);
    public void deleteTeacher(Teachers teachers);
    public List<Teachers> selectByName(String value);
    public void deleteBanco(final Long id);
}
