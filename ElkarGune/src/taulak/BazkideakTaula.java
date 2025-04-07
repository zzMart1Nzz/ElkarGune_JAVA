package taulak;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objetuak.Bazkidea;

public class BazkideakTaula extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Bazkidea> bazkidea;
    private String[] columnNames = {
        "idBazkidea", "NAN", "Izena", "Abizena", "Erabiltzailea", "Pasahitza", "Telefonoa", "Email", "KontuKorrontea", "Mota", "InskripzioData"
    };

    public BazkideakTaula(List<Bazkidea> bazkidea) {
        this.bazkidea = bazkidea;
    }

    @Override
    public int getRowCount() {
        return bazkidea.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bazkidea ba = bazkidea.get(rowIndex);
        switch (columnIndex) {
            case 0: return ba.getIdBazkidea();
            case 1: return ba.getNan();
            case 2: return ba.getIzena();
            case 3: return ba.getAbizena();
            case 4: return ba.getErabiltzailea();
            case 5: return ba.getPasahitza();
            case 6: return ba.getTelefonoZkia();
            case 7: return ba.getPostaElektronikoa();
            case 8: return ba.getKontuKorronteZkia();
            case 9: return ba.isAdmin() ? "Admin" : "Bazkidea";
            case 10: 
                if (ba.getInskripzioData() != null) {
                    return new SimpleDateFormat("yyyy/MM/dd").format(ba.getInskripzioData());
                } else {
                    return "Fecha no disponible";
                }
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