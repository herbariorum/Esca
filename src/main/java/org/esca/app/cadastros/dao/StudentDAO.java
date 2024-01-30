package org.esca.app.cadastros.dao;


import org.esca.app.cadastros.dominio.Students;

import java.util.List;

public interface StudentDAO {
    public Students getById(final Long id);
    public void addStudent(Students student);
    public void updateStudent(Students student);
    public void deleteStudent(Students student);
    public List<Students> selectAddress();
    public List<Students> selectByName(String value);
}
