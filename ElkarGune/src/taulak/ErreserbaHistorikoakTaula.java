package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.ErreserbaHistorikoa;

public class ErreserbaHistorikoakTaula extends AbstractTableModel {

    private List<ErreserbaHistorikoa> erreserbHist;
    private String[] columnNames = {
        "idErreserbaHistorikoa", "ErresebaZkia", "idBazkidea", "Mota", "Data"
    };

    public ErreserbaHistorikoakTaula(List<ErreserbaHistorikoa> erreserbHist) {
        this.erreserbHist = erreserbHist;
    }

    @Override
    public int getRowCount() {
        return erreserbHist.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ErreserbaHistorikoa eh = erreserbHist.get(rowIndex);
        switch (columnIndex) {
            case 0: return eh.getIdErreserba();
            case 1: return eh.getErreserbaZkia();
            case 2: return eh.getIdBazkidea();
            case 3: return eh.isMota() ? "Bazkaria": "Afaria";
            case 4: return new SimpleDateFormat("yyyy/MM/dd").format(eh.getData());
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
