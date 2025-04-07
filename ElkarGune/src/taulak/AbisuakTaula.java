package taulak;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Abisuak;

public class AbisuakTaula extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Abisuak> abisuak;
    private String[] columnNames = {
        "idAbisua", "Mezua", "Data"
    };

    public AbisuakTaula(List<Abisuak> abisuak) {
        this.abisuak = abisuak;
    }

    @Override
    public int getRowCount() {
        return abisuak.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Abisuak ab = abisuak.get(rowIndex);
        switch (columnIndex) {
            case 0: return ab.getIdAbisuak();
            case 1: return ab.getMezua();
            case 2: return new SimpleDateFormat("yyyy/MM/dd").format(ab.getData());
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
