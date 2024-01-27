package org.esca.app.cadastros.dao;

import org.esca.app.cadastros.dominio.Municipio;
import java.util.List;
public interface MunicipioDAO {

    public List<Municipio> listaMunicipio();

    public List<Municipio> listaMunicipioPorUf(final String uf);

    public void comboBoxMunicipio(final String uf);
}
