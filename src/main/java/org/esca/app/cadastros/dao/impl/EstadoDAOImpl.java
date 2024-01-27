package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.esca.app.cadastros.dao.EstadoDAO;
import org.esca.app.cadastros.dominio.Estado;
import org.esca.app.config.HibernateConfig;
import org.esca.app.util.ComboBoxList;
import org.esca.app.util.SComboBoxList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EstadoDAOImpl implements EstadoDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;
    private ArrayList<ComboBoxList> list;

    public EstadoDAOImpl(){
        this.em = hc.getEntityManager();
    }
    @Override
    public List<Estado> selectEstados() {
        return em.createQuery("FROM "+Estado.class.getName()).getResultList() ;
    }

    @Override
    public void comboBoxEstado() {
        this.setList(new ArrayList<>());
        List<Estado> estadoList = em.createQuery("SELECT e FROM Estado e ORDER BY e.nome ASC", Estado.class).getResultList();
        for (Estado e : estadoList){
            this.getList().add(new SComboBoxList(e.getId(), e.getUf(), e.getNome()));
        }
    }
}
