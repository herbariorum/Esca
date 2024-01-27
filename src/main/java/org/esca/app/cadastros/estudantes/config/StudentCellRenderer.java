package org.esca.app.cadastros.estudantes.config;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class StudentCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table, value, isSelect, hasFocus, row, column);

        if (row % 2 == 0){
            setBackground(new Color(255, 255, 255));
            setForeground(Color.BLACK);
        }else{
            setBackground(new Color(220, 220, 220));
            setForeground(Color.BLACK);
        }

        if (isSelect){
            setBackground(new Color(3, 167, 154));
            setForeground(Color.WHITE);
        }


        TableColumn hide = table.getColumnModel().getColumn(0); // idStudent
        hide.setMinWidth(0);
        hide.setMaxWidth(0);
        hide.setPreferredWidth(0);
        TableColumn hide2 = table.getColumnModel().getColumn(1);// address_id
        hide2.setMinWidth(0);
        hide2.setMaxWidth(0);
        hide2.setPreferredWidth(0);

        table.getColumnModel().getColumn(2).setMaxWidth(700); // Nome
        table.getColumnModel().getColumn(3).setMaxWidth(250); // CPF
        table.getColumnModel().getColumn(4).setMaxWidth(800); // Mae
        table.getColumnModel().getColumn(5).setMaxWidth(150); // Sexo
        table.getColumnModel().getColumn(6).setMaxWidth(200); // DAta





        return this;

    }
}
