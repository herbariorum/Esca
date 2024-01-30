package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.dao.StudentDAO;
import org.esca.app.cadastros.dominio.Students;
import org.esca.app.config.HibernateConfig;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;

    public StudentDAOImpl(){
        this.em = hc.getEntityManager();
    }
    @Override
    public Students getById(Long id) {
        return em.find(Students.class, id);
    }

    @Override
    public List<Students> selectAddress() {
        return em.createQuery("FROM "+ Students.class.getName()).getResultList();
    }

    @Override
    public List<Students> selectByName(String value) {
        List<Students> std = null;
        TypedQuery<Students> query = em.createQuery("SELECT s FROM Students s WHERE lower(s.nome) LIKE lower(:nome)", Students.class);
        query.setParameter("nome", "%"+value+"%");
        std = query.getResultList();
        return std;
    }

    @Override
    public void addStudent(Students student) {
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
    public void updateStudent(Students student) {
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Error "+e);
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteStudent(Students student) {
        try {
            em.getTransaction().begin();
            student = em.find(Students.class, student.getId());
            em.remove(student);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

}
