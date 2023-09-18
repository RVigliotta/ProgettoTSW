package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalogo implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Prodotto> list = new ArrayList<>();
	
	public List<Prodotto> getCatalogo() {
		return list;
	}
	
	public void add(Prodotto p) {
		list.add(p);
	}
	
	public void empty() {
		list.clear();
	}
}