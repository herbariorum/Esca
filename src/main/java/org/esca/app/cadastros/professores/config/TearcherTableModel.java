package org.esca.app.cadastros.professores.config;

import org.esca.app.cadastros.dominio.Teachers;
import org.esca.app.util.Util;
import org.esca.app.util.ViewAbstractTableModel;

import java.util.List;

public class TearcherTableModel extends ViewAbstractTableModel<Teachers> {

    public TearcherTableModel(List<Teachers> rows) {
        super(rows);
        columns = new String[]{
                "ID",
                "NOME",
                "CPF",
                "CARGO",
                "TELEFONE",
                "NASCIMENTO"
        };

    }

    @Override
    public Object getValueAt(int row, int col) {
        Teachers std = this.rows.get(row);
        switch (col) {
            case 0:
                return std.getId();
            case 1:
                return std.getNome();
            case 2:
                return std.getCpf();
            case 3:
                return std.getCargo();
            case 4:
                return std.getTelefone();
            case 5:
                return new Util().formatDate(std.getDta_nasc());
            default:
                return null;
        }
    }
}
