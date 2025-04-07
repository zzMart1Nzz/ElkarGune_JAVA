package taulak;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Produktua;

public class ProduktuakTaula extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Produktua> produktua;
    private String[] columnNames = {
        "idProduktia", "Kategoria", "Izena", "Erosketa Prezioa", "Salmenta Prezioa", "Stock", "idHornitzailea"
    };

    public ProduktuakTaula(List<Produktua> produktua) {
        this.produktua = produktua;
    }

    @Override
    public int getRowCount() {
        return produktua.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produktua pr = produktua.get(rowIndex);
        switch (columnIndex) {
            case 0: return pr.getIdProduktua();
            case 1: return pr.getKategoria();
            case 2: return pr.getIzena();
            case 3: return pr.getErosketaPrezioa();
            case 4: return pr.getSalmentaPrezioa();
            case 5: return pr.getStock();
            case 6: return pr.getIdHornitzailea();
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