package taulak;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Hornitzailea;

public class HornitzaileakTaula extends AbstractTableModel {

    private List<Hornitzailea> hornitzailea;
    private String[] columnNames = {
        "idHornitzailea", "Izena", "NIF", "Helbidea", "Telefonoa", "Email"
    };

    public HornitzaileakTaula(List<Hornitzailea> hornitzailea) {
        this.hornitzailea = hornitzailea;
    }

    @Override
    public int getRowCount() {
        return hornitzailea.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hornitzailea ho = hornitzailea.get(rowIndex);
        switch (columnIndex) {
            case 0: return ho.getIdHornitzailea();
            case 1: return ho.getIzena();
            case 2: return ho.getNif();
            case 3: return ho.getHelbidea();
            case 4: return ho.getTelefonoZkia();
            case 5: return ho.getPostaElektronikoa();
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
