package org.esca.app.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class SComboBoxList extends ComboBoxList{

    private String uf;

    public SComboBoxList(Long id, String uf, String nome) {
        super(id, nome);
        this.uf = uf;
    }

    public void setSelectedKey(ArrayList<SComboBoxList> comboBoxList, String id, javax.swing.JComboBox cb){
        for (SComboBoxList nome :  comboBoxList){
            if (nome.getId().equals(id)){
                cb.setSelectedItem(nome);
            }
        }
    }

    @Override
    public String toString() {
        return this.uf;
    }
}
