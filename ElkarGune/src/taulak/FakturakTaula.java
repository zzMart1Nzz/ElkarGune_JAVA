package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Fakturak;

public class FakturakTaula extends AbstractTableModel {

    private List<Fakturak> fakturak;
    private String[] columnNames = {
        "idFaktura", "idBazkidea", "ErreserbaZkia", "Data", "Totala","Ordainduta", "FakturaPDF"
    };

    public FakturakTaula(List<Fakturak> fakturak) {
        this.fakturak = fakturak;
    }

    @Override
    public int getRowCount() {
        return fakturak.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fakturak fa = fakturak.get(rowIndex);
        switch (columnIndex) {
            case 0: return fa.getIdFaktura();
            case 1: return fa.getIdBazkidea();
            case 2: return fa.getErreserbaZkia();
            case 3: return new SimpleDateFormat("yyyy/MM/dd").format(fa.getData());
            case 4: return fa.getTotala();
            case 5: return fa.isEgoera();
            case 6:	return fa.getFakturaPDF();
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
