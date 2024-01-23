package org.esca.app.auth.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.esca.app.auth.dao.UsuarioDAO;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.config.HibernateConfig;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;

    public UsuarioDAOImpl() {
        this.em = hc.getEntityManager();
    }

    @Override
    public Usuarios getById(Long id) {
        return em.find(Usuarios.class, id);
    }

    @Override
    public List<Usuarios> selectUser() {
        return em.createQuery("FROM " + Usuarios.class.getName()).getResultList();
    }

    @Override
    public void addUser(Usuarios usuarios) {
        try {
            em.getTransaction().begin();
            em.persist(usuarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateUser(Usuarios usuarios) {
        try {
            em.getTransaction().begin();
            em.merge(usuarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteUser(Usuarios usuarios) {
        try {
            em.getTransaction().begin();
            usuarios = em.find(Usuarios.class, usuarios.getId());
            em.remove(usuarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public Usuarios loginUser(Usuarios usuarios) {
        return null;
    }

    @Override
    public Usuarios selectEmailUser(Usuarios usuarios) {
        Usuarios userInfo = null;
        TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email =:email", Usuarios.class);
        query.setParameter("email", usuarios.getEmail());
        try {
            userInfo = query.getSingleResult();
        } catch (NoResultException | NullPointerException e) {
            userInfo = null;
        }
        return userInfo;
    }

    @Override
    public List<Usuarios> selectByValue(String value) {
        List<Usuarios> user = null;
        TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.nome LIKE :nome", Usuarios.class);
        query.setParameter("nome", "%" + value + "%");
        user = query.getResultList();
        return user;
    }
}
