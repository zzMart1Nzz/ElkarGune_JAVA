package objetuak;

import java.sql.Timestamp;

public class Fakturak {

	private int idFaktura;
	private int idBazkidea;
	private int erreserbaZkia;
	private Timestamp data;
	private double totala;
	private boolean egoera;
	
	//KONSTRUKTOREAK
	public Fakturak(int idFaktura, int idBazkidea, int erreserbaZkia, Timestamp data, double totala,
			boolean egoera) {
		this.idFaktura = idFaktura;
		this.idBazkidea = idBazkidea;
		this.erreserbaZkia = erreserbaZkia;
		this.data = data;
		this.totala = totala;
		this.egoera = egoera;
	}
	
	
	//GETTERRAK ETA SETTERRAK
	public int getIdFaktura() {
		return idFaktura;
	}

	public void setIdFaktura(int idFaktura) {
		this.idFaktura = idFaktura;
	}

	public int getIdBazkidea() {
		return idBazkidea;
	}

	public void setIdBazkidea(int idBazkidea) {
		this.idBazkidea = idBazkidea;
	}

	public int getErreserbaZkia() {
		return erreserbaZkia;
	}

	public void setErreserbaZkia(int erreserbaZkia) {
		this.erreserbaZkia = erreserbaZkia;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getTotala() {
		return totala;
	}

	public void setTotala(double totala) {
		this.totala = totala;
	}
	

	public boolean isEgoera() {
		return egoera;
	}


	public void setEgoera(boolean egoera) {
		this.egoera = egoera;
	}


	
	

	
}