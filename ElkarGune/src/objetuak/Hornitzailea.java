package objetuak;

public class Hornitzailea {

	private int idHornitzailea;
	private String izena;
	private String nif;
	private String helbidea;
	private String telefonoZkia;
	private String postaElektronikoa;

	//KONSTRUKTOREAK
	public Hornitzailea(int idHornitzailea, String izena, String nif, String helbidea, String telefonoZkia,
			String postaElektronikoa) {
		this.idHornitzailea = idHornitzailea;
		this.izena = izena;
		this.nif = nif;
		this.helbidea = helbidea;
		this.telefonoZkia = telefonoZkia;
		this.postaElektronikoa = postaElektronikoa;
	}

	//GETTERRAK ETA SETTERRAK
	public int getIdHornitzailea() {
		return idHornitzailea;
	}

	public void setIdHornitzailea(int idHornitzailea) {
		this.idHornitzailea = idHornitzailea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getHelbidea() {
		return helbidea;
	}

	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}

	public String getTelefonoZkia() {
		return telefonoZkia;
	}

	public void setTelefonoZkia(String telefonoZkia) {
		this.telefonoZkia = telefonoZkia;
	}

	public String getPostaElektronikoa() {
		return postaElektronikoa;
	}

	public void setPostaElektronikoa(String postaElektronikoa) {
		this.postaElektronikoa = postaElektronikoa;
	}



}
