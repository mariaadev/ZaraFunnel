package com.example.zarafunnel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Product> productList;
    private final Context context;

    public CartAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = productList.get(position);  // Obtén el producto

        holder.productName.setText(product.getName());    // Asignar nombre
        holder.productPrice.setText(product.getPrice());  // Asignar precio
        holder.productSize.setText("Talla: " + product.getSize()); // Asignar talla

        if (product.getImageResId() != 0) {
            holder.productImage.setImageResource(product.getImageResId());  // Asignar imagen
        } else {
            holder.productImage.setImageResource(R.drawable.default_image);  // Imagen por defecto si no existe
        }
    }

    @Override
    public int getItemCount() {
        return productList.size(); // Debería devolver el número de productos en la lista
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productSize;
        ImageView productImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productSize = itemView.findViewById(R.id.textProductSize);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}
