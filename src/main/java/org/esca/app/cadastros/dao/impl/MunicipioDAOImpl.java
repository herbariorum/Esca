package org.esca.app.cadastros.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;
import org.esca.app.cadastros.dao.MunicipioDAO;
import org.esca.app.cadastros.dominio.Estado;
import org.esca.app.cadastros.dominio.Municipio;
import org.esca.app.config.HibernateConfig;
import org.esca.app.util.ComboBoxList;
import org.esca.app.util.SComboBoxList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MunicipioDAOImpl implements MunicipioDAO {
    private final HibernateConfig hc = new HibernateConfig();
    private final EntityManager em;
    private ArrayList<ComboBoxList> list;

    public MunicipioDAOImpl() {
        this.em = hc.getEntityManager();
    }

    @Override
    public List<Municipio> listaMunicipio() {
        return em.createQuery("FROM "+Municipio.class.getName()).getResultList();
    }

    @Override
    public List<Municipio> listaMunicipioPorUf(String uf) {
        List<Municipio> municipios = null;
        TypedQuery<Municipio> query = em.createQuery("SELECT m FROM Municipio m WHERE m.uf = :uf", Municipio.class);
        query.setParameter("uf", uf);
        municipios = query.getResultList();
        return municipios;
    }

    @Override
    public void comboBoxMunicipio(String uf) {
        List<Municipio> municipios = null;
        this.setList(new ArrayList<>());
        TypedQuery<Municipio> query = em.createQuery("SELECT m FROM Municipio m WHERE m.uf = :uf", Municipio.class);
        query.setParameter("uf", uf);
        municipios = query.getResultList();
        for (Municipio m: municipios){
            this.getList().add(new ComboBoxList(m.getId(), m.getNome()));
        }
    }

//    @Override
//    public void comboBoxMunicipio() {
//        this.setList(new ArrayList<>());
//        List<Municipio> municipioList = em.createQuery("SELECT m FROM Municipio m ORDER BY m.nome ASC", Municipio.class).getResultList();
//        for (Municipio m : municipioList){
//            this.getList().add(new ComboBoxList(m.getId(), m.getNome()));
//        }
//    }
}
