package com.example.zarafunnel.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static  List<Product> cart = new ArrayList<>();

    // Método para agregar un producto al carrito
    public static void addToCart(Product product) {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(product);
    }

    // Método para obtener el carrito
    public static List<Product> getCart() {
        return cart;
    }

    // Limpiar el carrito
    public static void clearCart() {
        cart.clear();
    }
}

