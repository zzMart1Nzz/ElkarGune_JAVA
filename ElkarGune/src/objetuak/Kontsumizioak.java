package objetuak;



public class Kontsumizioak {

	private int idKontsumizioa;
	private int erreserbaZkia;
	private int idFaktura;
	private int idProduktua;
	private int kopurua;
	private double prezioa;


	//KONSTRUKTOREAK
	public Kontsumizioak(int idKontsumizioa, int erreserbaZkia, int idFaktura, int idProduktua, int kopurua,
			double prezioa) {
		this.idKontsumizioa = idKontsumizioa;
		this.erreserbaZkia = erreserbaZkia;
		this.idFaktura = idFaktura;
		this.idProduktua = idProduktua;
		this.kopurua = kopurua;
		this.prezioa = prezioa;

	}


	//GETTERRAK ETA SETTERRAK
	public int getIdKontsumizioa() {
		return idKontsumizioa;
	}

	public void setIdKontsumizioa(int idKontsumizioa) {
		this.idKontsumizioa = idKontsumizioa;
	}

	public int getErreserbaZkia() {
		return erreserbaZkia;
	}

	public void setErreserbaZkia(int erreserbaZkia) {
		this.erreserbaZkia = erreserbaZkia;
	}

	public int getIdFaktura() {
		return idFaktura;
	}

	public void setIdFaktura(int idFaktura) {
		this.idFaktura = idFaktura;
	}

	public int getIdProduktua() {
		return idProduktua;
	}

	public void setIdProduktua(int idProduktua) {
		this.idProduktua = idProduktua;
	}

	public int getKopurua() {
		return kopurua;
	}

	public void setKopurua(int kopurua) {
		this.kopurua = kopurua;
	}

	public double getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(double prezioa) {
		this.prezioa = prezioa;
	}





}