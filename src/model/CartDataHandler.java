package model;

import com.google.gson.Gson;

public class CartDataHandler {
    private Gson gson = new Gson();

    // Converte un oggetto Cart in una stringa JSON
    public String convertCartToJsonString(Cart cart) {
        return gson.toJson(cart);
    }

    // Converte una stringa JSON in un oggetto Cart
    public Cart convertJsonStringToCart(String jsonString) {
        return gson.fromJson(jsonString, Cart.class);
    }
}

