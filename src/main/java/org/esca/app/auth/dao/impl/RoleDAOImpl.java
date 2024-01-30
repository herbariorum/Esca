package org.esca.app.auth.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.esca.app.auth.dao.RoleDAO;
import org.esca.app.auth.dominio.Roles;
import org.esca.app.config.HibernateConfig;
import org.esca.app.util.ComboBoxList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleDAOImpl implements RoleDAO {

    private final HibernateConfig hc = new HibernateConfig();
    private EntityManager em;
    private ArrayList<ComboBoxList> list;

    public RoleDAOImpl() {
        this.em = hc.getEntityManager();
    }

    @Override
    public Roles getById(Long id) {
        return em.find(Roles.class, id);
    }

    @Override
    public List<Roles> selectRole() {
        return em.createQuery("FROM "+Roles.class.getName()).getResultList();
    }

    @Override
    public void addRole(Roles role) {
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateRole(Roles role) {
        try {
            em.getTransaction().begin();
            em.merge(role);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteRole(Roles role) {
        try {
            em.getTransaction().begin();
            role = em.find(Roles.class, role.getId());
            em.remove(role);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }


    @Override
    public void comboBoxRole() {
        this.setList(new ArrayList<>());
        List<Roles> rolesList = em.createQuery("SELECT r FROM Roles r ORDER BY r.role ASC", Roles.class).getResultList();
        for (Roles r: rolesList){
            this.getList().add(new ComboBoxList(r.getId(), r.getRole()));
        }
    }
}
