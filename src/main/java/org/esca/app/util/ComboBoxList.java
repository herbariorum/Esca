package org.esca.app.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class ComboBoxList {
    
    private Long id;
    private String name;
    
    public ComboBoxList(Long id, String name){
        this.id = id;
        this.name = name;
    }
    
    public void setSelectedId(ArrayList<ComboBoxList> comboBoxList, String id, javax.swing.JComboBox cb){
        for (ComboBoxList nome :  comboBoxList){
            if (nome.getId().toString().equals(id)){
                cb.setSelectedItem(nome);
            }
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}
