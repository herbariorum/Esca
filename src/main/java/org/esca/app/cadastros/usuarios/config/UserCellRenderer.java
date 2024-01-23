
package org.esca.app.cadastros.usuarios.config;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;


public class UserCellRenderer extends DefaultTableCellRenderer{
    
    
    
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
        


        TableColumn hide = table.getColumnModel().getColumn(0); // idUser
        hide.setMinWidth(0);
        hide.setMaxWidth(0);
        hide.setPreferredWidth(0);
        TableColumn hide1 = table.getColumnModel().getColumn(1); // idRole
        hide1.setMinWidth(0);
        hide1.setMaxWidth(0);
        hide1.setPreferredWidth(0);
        
        table.getColumnModel().getColumn(2).setMaxWidth(800); // Nome
        table.getColumnModel().getColumn(3).setMaxWidth(600); // Email
        table.getColumnModel().getColumn(4).setMaxWidth(300); // cargo
        table.getColumnModel().getColumn(5).setMaxWidth(200); // telefone

        TableColumn hide2 = table.getColumnModel().getColumn(6);// created_at
        hide2.setMinWidth(0);
        hide2.setMaxWidth(0);
        hide2.setPreferredWidth(0);

        table.getColumnModel().getColumn(7).setMaxWidth(200); // ROLE

        return this;
        
    }
}
