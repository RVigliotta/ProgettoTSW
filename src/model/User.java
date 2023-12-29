package model;

public class User {
    private String nome;
    private String cognome;
    private String username;
    private String indirizzo;
    private String datiPagamento;
	private String Email;

    // Costruttore vuoto
    public User() {
    }

    // Metodi getter e setter per ciascun campo
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDatiPagamento() {
        return datiPagamento;
    }

    public void setDatiPagamento(String datiPagamento) {
        this.datiPagamento = datiPagamento;
    }
}

