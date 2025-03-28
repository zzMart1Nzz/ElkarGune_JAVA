package objetuak;

public class Produktua {
    private int idProduktua;
    private int idProduktuMota;
    private String izena;
    private double erosketaPrezioa;
    private double salmentaPrezioa;
    private int stock;
    private int idHornitzailea;
    private String kategoria;  // Nuevo campo para la categoría

    // Constructor actualizado para incluir kategoria
    public Produktua(int idProduktua, int idProduktuMota, String izena, double erosketaPrezioa,
                     double salmentaPrezioa, int stock, int idHornitzailea, String kategoria) {
        this.idProduktua = idProduktua;
        this.idProduktuMota = idProduktuMota;
        this.izena = izena;
        this.erosketaPrezioa = erosketaPrezioa;
        this.salmentaPrezioa = salmentaPrezioa;
        this.stock = stock;
        this.idHornitzailea = idHornitzailea;
        this.kategoria = kategoria;  // Asignamos la kategoria
    }

	public int getIdProduktua() {
		return idProduktua;
	}

	public void setIdProduktua(int idProduktua) {
		this.idProduktua = idProduktua;
	}

	public int getIdProduktuMota() {
		return idProduktuMota;
	}

	public void setIdProduktuMota(int idProduktuMota) {
		this.idProduktuMota = idProduktuMota;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public double getErosketaPrezioa() {
		return erosketaPrezioa;
	}

	public void setErosketaPrezioa(double erosketaPrezioa) {
		this.erosketaPrezioa = erosketaPrezioa;
	}

	public double getSalmentaPrezioa() {
		return salmentaPrezioa;
	}

	public void setSalmentaPrezioa(double salmentaPrezioa) {
		this.salmentaPrezioa = salmentaPrezioa;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIdHornitzailea() {
		return idHornitzailea;
	}

	public void setIdHornitzailea(int idHornitzailea) {
		this.idHornitzailea = idHornitzailea;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}


}
