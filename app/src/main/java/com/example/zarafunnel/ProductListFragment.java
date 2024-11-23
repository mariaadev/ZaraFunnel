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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Jeans TRF Wide Leg", "29,95 EUR", R.drawable.profile));
        productList.add(new Product("Camiseta Rib Effect", "9,95 EUR", R.drawable.profile));
        productList.add(new Product("Jeans TRF Wide Leg", "29,95 EUR", R.drawable.profile));
        productList.add(new Product("Camiseta Rib Effect", "9,95 EUR", R.drawable.profile));
        productList.add(new Product("Jeans TRF Wide Leg", "29,95 EUR", R.drawable.profile));
        productList.add(new Product("Camiseta Rib Effect", "9,95 EUR", R.drawable.profile));

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
