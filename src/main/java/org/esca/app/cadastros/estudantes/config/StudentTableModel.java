package org.esca.app.cadastros.estudantes.config;

import org.esca.app.cadastros.dominio.Students;
import org.esca.app.util.Util;
import org.esca.app.util.ViewAbstractTableModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentTableModel extends ViewAbstractTableModel<Students> {

    public StudentTableModel(List<Students> rows) {
        super(rows);
        columns = new String[]{
                "ID",
                "NOME",
                "CPF",
                "MÃE",
                "SEXO",
                "NASCIMENTO"
        };

    }

    @Override
    public Object getValueAt(int row, int col) {
        Students std = this.rows.get(row);
        switch (col) {
            case 0:
                return std.getId();
            case 1:
                return std.getNome();
            case 2:
                return std.getCpf();
            case 3:
                return std.getNomeMae();
            case 4:
                return std.getSexo();
            case 5:
                return new Util().formatDate(std.getDtaNascimento());
            default:
                return null;
        }
    }

    //id address_id nome cpf mae sexo data_naascimento
}
