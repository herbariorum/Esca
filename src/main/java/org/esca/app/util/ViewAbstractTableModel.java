
package org.esca.app.util;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public abstract class ViewAbstractTableModel<E> extends AbstractTableModel {
    
    // lida com as linhas
    protected List<E> rows;
    // lida com as colunas
    protected String[] columns;

    public ViewAbstractTableModel(List<E> rows) {
        this.rows = rows;
    }
    
    // indica o número de linhas que a tabela contém
    @Override
    public int getRowCount(){
        return rows.size();
    }
    
    @Override
    public int getColumnCount(){
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column){
        if (column < getColumnCount())
            return columns[column];
        return super.getColumnName(column);
    }
    
    public E getValueAtRows(int row){
        return rows.get(row);
    }
    
    public void setValueAtRow(int row, E object){
        rows.set(row, object);
    }
   
}
