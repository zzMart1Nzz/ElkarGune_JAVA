package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Erreserba;

public class ErreserbakTaula extends AbstractTableModel {

    private List<Erreserba> erreserba;
    private String[] columnNames = {
        "idErreserba", "ErreserbaZkia", "idBazkidea", "Mota", "Data"
    };

    public ErreserbakTaula(List<Erreserba> erreserba) {
        this.erreserba = erreserba;
    }

    @Override
    public int getRowCount() {
        return erreserba.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Erreserba er = erreserba.get(rowIndex);
        switch (columnIndex) {
            case 0: return er.getIdErreserba();
            case 1: return er.getErreserbaZkia();
            case 2: return er.getIdBazkidea();
            case 3: return er.isMota() ? "Bazkaria" : "Afaria";
            case 4: return new SimpleDateFormat("yyyy/MM/dd").format(er.getData());
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column >= 0 && column < columnNames.length) {
            return columnNames[column];
        }
        return null;
    }
}