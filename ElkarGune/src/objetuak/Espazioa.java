package objetuak;

public class Espazioa {

	private int idEspazioa;
	private String izena;
	private int gaitasuna;
	private boolean egoera;

	//KONSTRUKTOREAK
	public Espazioa(int idEspazioa, String izena, int gaitasuna, boolean egoera) {
		this.idEspazioa = idEspazioa;
		this.izena = izena;
		this.gaitasuna = gaitasuna;
		this.egoera = egoera;
	}

	//GETTERRAK ETA SETTERRAK
	public int getIdEspazioa() {
		return idEspazioa;
	}

	public void setIdEspazioa(int idEspazioa) {
		this.idEspazioa = idEspazioa;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public int getGaitasuna() {
		return gaitasuna;
	}

	public void setGaitasuna(int gaitasuna) {
		this.gaitasuna = gaitasuna;
	}

	public boolean isEgoera() {
		return egoera;
	}

	public void setEgoera(boolean egoera) {
		this.egoera = egoera;
	}





}
