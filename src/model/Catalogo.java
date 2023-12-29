package model;

import java.util.ArrayList;

public class Catalogo {
	private ArrayList<ProductBean> catalogo = new ArrayList<>();
	
	public ArrayList<ProductBean> getCatalogo(){
		return catalogo;
	}

	public void setCarrello(ArrayList<ProductBean> carrello) {
		this.catalogo = carrello;
	}
	public void add(ProductBean prodotto) {
		catalogo.add(prodotto);
	}
	public void remove(ProductBean prodotto) {
		catalogo.clear();
	}
}
