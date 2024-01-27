package org.esca.app.cadastros.dao;

import org.esca.app.cadastros.dominio.Estado;

import java.util.List;

public interface EstadoDAO {
    public List<Estado> selectEstados();
    public void comboBoxEstado();
}
