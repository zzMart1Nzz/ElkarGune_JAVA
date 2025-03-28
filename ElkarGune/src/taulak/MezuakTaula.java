package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Mezuak;

public class MezuakTaula extends AbstractTableModel {

    private List<Mezuak> mezuak;
    private String[] columnNames = {
        "idMezua", "idBazkidea", "Mezua", "Data"
    };

    public MezuakTaula(List<Mezuak> mezuak) {
        this.mezuak = mezuak;
    }

    @Override
    public int getRowCount() {
        return mezuak.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mezuak me = mezuak.get(rowIndex);
        switch (columnIndex) {
            case 0: return me.getIdMezua();
            case 1: return me.getIdBazkidea();
            case 2: return me.getMezua();
            case 3: return new SimpleDateFormat("yyyy/MM/dd").format(me.getData());
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