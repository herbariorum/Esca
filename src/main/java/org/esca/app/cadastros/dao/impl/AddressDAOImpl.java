package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.esca.app.cadastros.dao.AddressDAO;
import org.esca.app.cadastros.dominio.Address;
import org.esca.app.config.HibernateConfig;

import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;

    public AddressDAOImpl() {
        this.em = hc.getEntityManager();
    }

    @Override
    public Address getById(Long id) {
        return em.find(Address.class, id);
    }

    @Override
    public List<Address> selectAddress() {
        return em.createQuery("FROM " + Address.class.getName()).getResultList();
    }

    @Override
    public void addAddress(Address address) {
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateAddress(Address address) {
        try {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteAddress(Address address) {
        try {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }


}
