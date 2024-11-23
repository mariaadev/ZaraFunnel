package com.example.zarafunnel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView recyclerView;

    public ProductListFragment() {}

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Lista de productos
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("JEANS TRF WIDE LEG", "29,95 EUR", R.drawable.model3_image4, ""));
        productList.add(new Product("CAMISETA RIB EFFECT", "9,95 EUR", R.drawable.model6_image4,""));
        productList.add(new Product("CAMISETA ALGODÓN", "9,95 EUR", R.drawable.model_image4,""));
        productList.add(new Product("CAMISETA RIB EFFECT", "9,95 EUR", R.drawable.model5_image4,""));
        productList.add(new Product("CAMISETA RIB EFFECT", "9,95 EUR", R.drawable.model4_image4, ""));
        productList.add(new Product("CAMISETA ALGODÓN", "9,95 EUR", R.drawable.model3_image4, ""));

        // Verifica si el contexto es adecuado para el listener
        if (getActivity() instanceof OnProductClickListener) {
            ProductAdapter adapter = new ProductAdapter(productList, (OnProductClickListener) getActivity());
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}

