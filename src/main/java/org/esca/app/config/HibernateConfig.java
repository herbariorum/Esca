package org.esca.app.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HibernateConfig {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    public HibernateConfig() {
         this.setFactory(Persistence.createEntityManagerFactory("esca_persistence"));
    }

    public EntityManager getEntityManager(){
        return entityManager = new HibernateConfig().getFactory().createEntityManager();
    }

    public void closeConnection(){
        entityManager.close();
        getFactory().close();
    }
}
