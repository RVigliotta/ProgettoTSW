package model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int code;
	String name;
	String description;
	String image;
	float price;
	int quantità_catalogo;
	int quantità_carrello;
	int CategoriaID;
	

	public ProductBean() {
		code = -1;
		name = "";
		image="";
		description = "";
		quantità_catalogo = 0;
		
		CategoriaID=0;
		price=(float) 0.00;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantità_catalogo;
	}

	public void setQuantity(int quantity) {
		this.quantità_catalogo = quantity;
	}
	
	public int getCategoriaID() {
		return CategoriaID;
	}

	public void setCategoriaID(int CategoriaID) {
		this.CategoriaID = CategoriaID;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image=image;
	}

	
	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantità_catalogo + ". " + description + CategoriaID + image;
	}

}
