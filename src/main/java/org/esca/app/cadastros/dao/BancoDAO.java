package org.esca.app.cadastros.dao;

import org.esca.app.cadastros.dominio.Banco;

public interface BancoDAO {
    public Banco getById(final Long id);
    public void addBanco(Banco banco);
    public void updateBanco(Banco banco);
    public void deleteBanco(Banco banco);
}
