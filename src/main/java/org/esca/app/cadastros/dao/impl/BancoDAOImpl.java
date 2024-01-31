package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import org.esca.app.cadastros.dao.BancoDAO;
import org.esca.app.cadastros.dominio.Banco;
import org.esca.app.cadastros.dominio.Teachers;
import org.esca.app.config.HibernateConfig;

public class BancoDAOImpl implements BancoDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;

    public BancoDAOImpl(){
        this.em = hc.getEntityManager();
    }
    @Override
    public Banco getById(Long id) {
        return this.em.find(Banco.class, id);
    }

    @Override
    public void addBanco(Banco banco) {
        try{
            em.getTransaction().begin();
            em.persist(banco);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateBanco(Banco banco) {
        try{
            em.getTransaction().begin();
            em.merge(banco);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteBanco(Banco banco) {
        try{
            em.getTransaction().begin();
            banco = em.find(Banco.class, banco.getId());
            em.remove(banco);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }
}
