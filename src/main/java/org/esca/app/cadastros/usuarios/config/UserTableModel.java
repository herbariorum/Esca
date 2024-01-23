package org.esca.app.cadastros.usuarios.config;


import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.util.ViewAbstractTableModel;

import java.util.List;


public class UserTableModel extends ViewAbstractTableModel<Usuarios> {

   
    public UserTableModel(List<Usuarios> rows) {
        super(rows);
        columns = new String[]{
                "ID",
                "ROLE ID",
                "NOME",
                "EMAIL",
                "CARGO",
                "TELEFONE",
                "CREATED_AT",
                "ROLE"
        };
        
    }

    @Override
    public Object getValueAt(int row, int col) {
        Usuarios user = rows.get(row);
        switch (col) {
            case 0:
                return user.getId();
            case 1:
                return user.getRole().getId();
            case 2:
                return user.getNome();
            case 3:
                return user.getEmail();
            case 4:
                return user.getCargo();
            case 5:
                return user.getPhone();
            case 6:
                return user.getCreated_at();
            case 7:
                return user.getRole().getRole();
            default:
                return null;
        }
    }
    
    public void atualizar(List<Usuarios> rows){
        this.rows = rows;
        fireTableDataChanged();
    }
}
