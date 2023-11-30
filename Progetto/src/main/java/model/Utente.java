package model;

import java.io.Serializable;

public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String nome;
	private String cognome;

	public Utente(int id, String email, String nome, String cognome) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}