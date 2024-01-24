package org.esca.app.cadastros.dao;


import org.esca.app.cadastros.dominio.Student;

import java.util.List;

public interface StudentDAO {
    public Student getById(final Long id);
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
    public List<Student> selectAddress();
    public List<Student> selectByName(String value);
}
