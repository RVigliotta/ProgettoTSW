package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdiniList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Ordine>list = new ArrayList<>();
	
	public List<Ordine> getOrdiniList() {
		return list;
	}

	public void add(Ordine o) {
		list.add(o);
	}
	
	public void empty() {
		list.clear();
	}
}