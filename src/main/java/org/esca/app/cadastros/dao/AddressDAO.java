package org.esca.app.cadastros.dao;


import org.esca.app.cadastros.dominio.Address;

import java.util.List;

public interface AddressDAO {
    public Address getById(final Long id);
    public void addAddress(Address address);
    public void updateAddress(Address address);
    public void deleteAddress(Address address);
    public List<Address> selectAddress();

}
