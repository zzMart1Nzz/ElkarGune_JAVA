package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Kontsumizioak;

public class KontsumizioakTaula extends AbstractTableModel {

    private List<Kontsumizioak> kontsumizioak;
    private String[] columnNames = {
        "idKontsumizioa", "ErreserbaZkia", "idFaktura", "idBodega", "Kopurua", "Prezioa", "Totala"
    };

    public KontsumizioakTaula(List<Kontsumizioak> kontsumizioak) {
        this.kontsumizioak = kontsumizioak;
    }

    @Override
    public int getRowCount() {
        return kontsumizioak.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kontsumizioak ko = kontsumizioak.get(rowIndex);
        switch (columnIndex) {
            case 0: return ko.getIdKontsumizioa();
            case 1: return ko.getErreserbaZkia();
            case 2: return ko.getIdFaktura();
            case 3: return ko.getIdProduktua();
            case 4: return ko.getKopurua();
            case 5: return ko.getPrezioa();
            case 6: return ko.getTotala();
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