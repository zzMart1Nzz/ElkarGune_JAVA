package objetuak;

import java.sql.Timestamp;

public class Abisuak {

	private int idAbisuak;
	private String mezua;
	private Timestamp data;

	//KONSTRUKTOREAK
	public Abisuak(int idAbisuak, String mezua, Timestamp data) {
		this.idAbisuak = idAbisuak;
		this.mezua = mezua;
		this.data = data;
	}

	//GETTERRAK ETA SETTERRAK
	public int getIdAbisuak() {
		return idAbisuak;
	}

	public void setIdAbisuak(int idAbisuak) {
		this.idAbisuak = idAbisuak;
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
