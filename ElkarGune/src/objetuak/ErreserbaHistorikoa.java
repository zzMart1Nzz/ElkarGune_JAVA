package objetuak;

import java.sql.Timestamp;

public class ErreserbaHistorikoa {

	private int idErreserba;
	private int idBazkidea;
	private boolean mota;
	private Timestamp data;

	//KONSTRUKTOREAK
	public ErreserbaHistorikoa(int idErreserba, int idBazkidea, boolean mota, Timestamp data) {
		this.idErreserba = idErreserba;
		this.idBazkidea = idBazkidea;
		this.mota = mota;
		this.data = data;
	}

	//GETTERRAK ETA SETTERRAK
	public int getIdErreserba() {
		return idErreserba;
	}

	public void setIdErreserba(int idErreserba) {
		this.idErreserba = idErreserba;
	}

	public int getIdBazkidea() {
		return idBazkidea;
	}

	public void setIdBazkidea(int idBazkidea) {
		this.idBazkidea = idBazkidea;
	}

	public boolean isMota() {
		return mota;
	}

	public void setMota(boolean mota) {
		this.mota = mota;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}



}
