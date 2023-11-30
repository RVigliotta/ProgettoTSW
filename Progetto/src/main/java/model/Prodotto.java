package model;

import java.io.Serializable;

public class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codice;
	private String nome;
	private String descrizione;
	private String img;
	private String marca;
	private String categoria;
	private int quantita;
	private double prezzo;

	public Prodotto(String codice, String nome, String descrizione, String img, String marca, String categoria,
			int quantita, double prezzo) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.img = img;
		this.marca = marca;
		this.categoria = categoria;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Prodotto [codice=" + codice + ", nome=" + nome + ", descrizione=" + descrizione + ", img=" + img
				+ ", marca=" + marca + ", categoria=" + categoria + ", quantita=" + quantita + ", prezzo=" + prezzo
				+ "]";
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}