package objetuak;

import java.sql.Timestamp;

public class Bazkidea {

	private int idBazkidea;
	private String nan;
	private String izena;
	private String abizena;
	private String erabiltzailea;
	private String pasahitza;
	private String telefonoZkia;
	private String postaElektronikoa;
	private String kontuKorronteZkia;
	private boolean admin;
	private Timestamp inskripzioData;

	//KONSTRUKTOREAK
	public Bazkidea(int idBazkidea, String nan, String izena, String abizena, String erabiltzailea, String pasahitza,
			String telefonoZkia, String postaElektronikoa, String kontuKorronteZkia, boolean admin,
			Timestamp inskripzioData) {
		this.idBazkidea = idBazkidea;
		this.nan = nan;
		this.izena = izena;
		this.abizena = abizena;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.telefonoZkia = telefonoZkia;
		this.postaElektronikoa = postaElektronikoa;
		this.kontuKorronteZkia = kontuKorronteZkia;
		this.admin = admin;
		this.inskripzioData = inskripzioData;
	}


	public Bazkidea() {
		// TODO Auto-generated constructor stub
	}


	//GETTERRAK ETA SETTERRAK
	public int getIdBazkidea() {
		return idBazkidea;
	}

	public void setIdBazkidea(int idBazkidea) {
		this.idBazkidea = idBazkidea;
	}

	public String getNan() {
		return nan;
	}

	public void setNan(String nan) {
		this.nan = nan;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
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

	public String getKontuKorronteZkia() {
		return kontuKorronteZkia;
	}

	public void setKontuKorronteZkia(String kontuKorronteZkia) {
		this.kontuKorronteZkia = kontuKorronteZkia;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Timestamp getInskripzioData() {
		return inskripzioData;
	}

	public void setInskripzioData(Timestamp inskripzioData) {
		this.inskripzioData = inskripzioData;
	}



}