package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.dao.StudentDAO;
import org.esca.app.cadastros.dominio.Student;
import org.esca.app.config.HibernateConfig;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;

    public StudentDAOImpl(){
        this.em = hc.getEntityManager();
    }
    @Override
    public Student getById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> selectAddress() {
        return em.createQuery("FROM "+Student.class.getName()).getResultList();
    }

    @Override
    public List<Student> selectByName(String value) {
        List<Student> std = null;
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.nome LIKE :nome", Student.class);
        query.setParameter("nome", "%"+value+"%");
        std = query.getResultList();
        return std;
    }

    @Override
    public void addStudent(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteStudent(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }


}
