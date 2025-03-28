package taulak;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import objetuak.Espazioa;

public class EspazioakTaula extends AbstractTableModel {

    private List<Espazioa> espazioa;
    private String[] columnNames = {
        "idEspazioa", "Izena", "Gaitasuna", "Egoera"
    };

    public EspazioakTaula(List<Espazioa> espazioa) {
        this.espazioa = espazioa;
    }

    @Override
    public int getRowCount() {
        return espazioa.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Espazioa es = espazioa.get(rowIndex);
        switch (columnIndex) {
            case 0: return es.getIdEspazioa();
            case 1: return es.getIzena();
            case 2: return es.getGaitasuna();
            case 3: return es.isEgoera()? "Erabilgarria" : "Ez erabilgarria";
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
