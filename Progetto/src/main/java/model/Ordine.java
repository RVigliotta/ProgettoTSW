package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String data;
	private double totale;
	private int userId;
	private int stato;
	private int spedizione;
	private int idIndirizzo;
	private ArrayList<SingoloOrdine> singoli = new ArrayList<>();

	public Ordine(int id, String data, double totale, int userId, int stato, int spedizione, int idIndirizzo) {
		super();
		this.id = id;
		this.data = data;
		this.totale = totale;
		this.userId = userId;
		this.stato = stato;
		this.spedizione = spedizione;
		this.idIndirizzo = idIndirizzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public int getSpedizione() {
		return spedizione;
	}

	public void setSpedizione(int spedizione) {
		this.spedizione = spedizione;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public List<SingoloOrdine> getSingoli() {
		return singoli;
	}

	public void add(SingoloOrdine o) {
		singoli.add(o);
	}
}