package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;
import org.esca.app.cadastros.dao.TeachersDAO;
import org.esca.app.cadastros.dominio.Teachers;
import org.esca.app.config.HibernateConfig;
import org.esca.app.util.ComboBoxList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TearcherDAOImpl implements TeachersDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;
    private ArrayList<ComboBoxList> list;

    public TearcherDAOImpl(){
        this.em = hc.getEntityManager();
    }
    @Override
    public List<Teachers> selectByName(String value) {
        List<Teachers> teachers = null;
        TypedQuery<Teachers> query = em.createQuery("SELECT t FROM Teachers t WHERE lower(t.nome) LIKE lower(:nome)", Teachers.class);
        query.setParameter("nome", "%" + value + "%");
        teachers = query.getResultList();

        return teachers;
    }

    @Override
    public void deleteBanco(Long id) {
        try{
            em.getTransaction().begin();
            Teachers teachers = em.find(Teachers.class, id);
            em.remove(teachers.getConta_bancaria());
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public Teachers getById(Long id) {
        return em.find(Teachers.class, id);
    }

    @Override
    public void addTeacher(Teachers teachers) {
        try{
            em.getTransaction().begin();
            em.persist(teachers);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateTeacher(Teachers teachers) {
        try{
            em.getTransaction().begin();
            em.merge(teachers);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteTeacher(Teachers teachers) {
        try{
            em.getTransaction().begin();
            teachers = em.find(Teachers.class, teachers.getId());
            em.remove(teachers);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }


}
