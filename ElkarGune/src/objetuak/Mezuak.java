package objetuak;

import java.sql.Timestamp;

public class Mezuak {

	private int idMezua;
	private int idBazkidea;
	private String mezua;
	private Timestamp data;

	//KONSTRUKTOREAK
	public Mezuak(int idMezua, int idBazkidea, String mezua, Timestamp data) {
		this.idMezua = idMezua;
		this.idBazkidea = idBazkidea;
		this.mezua = mezua;
		this.data = data;
	}

	//GETTERRAK ETA SETTERRAK
	public int getIdMezua() {
		return idMezua;
	}

	public void setIdMezua(int idMezua) {
		this.idMezua = idMezua;
	}

	public int getIdBazkidea() {
		return idBazkidea;
	}

	public void setIdBazkidea(int idBazkidea) {
		this.idBazkidea = idBazkidea;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}



}
