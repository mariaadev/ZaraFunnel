package com.example.zarafunnel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zarafunnel.utils.OnProductClickListener;
import com.example.zarafunnel.models.Product;
import com.example.zarafunnel.adapters.ProductAdapter;
import com.example.zarafunnel.R;

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

        //Llista de productes
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("JEANS TRF WIDE LEG TIRO MEDIO", "29,95 EUR", R.drawable.model3_image4, ""));
        productList.add(new Product("CAMISETA RIB EFECTO LAVADO", "9,95 EUR", R.drawable.model6_image4,""));
        productList.add(new Product("CAMISETA ALGODÓN MODAL MANGA LARGA", "9,95 EUR", R.drawable.model_image4,""));
        productList.add(new Product("CAMISETA RIB EFECTO LAVADO", "9,95 EUR", R.drawable.model5_image4,""));
        productList.add(new Product("CAMISETA RIB EFECTO LAVADO", "9,95 EUR", R.drawable.model4_image4, ""));
        productList.add(new Product("CAMISETA ALGODÓN MODAL MANGA LARGA", "9,95 EUR", R.drawable.model3_image4, ""));
        productList.add(new Product("JEANS Z1975 DENIM RECTOS TIRO ALTO", "25,95 EUR", R.drawable.trfalto, ""));
        productList.add(new Product("CAMISA FLUIDA BOTÓN DORADO", "22,95", R.drawable.camisa_zara,""));
        productList.add(new Product("CAMISA ENTALLADA POPELÍN", "22,95 EUR", R.drawable.popelin, ""));
        productList.add(new Product("JEANS TRF MOM FIT TIRO ALTO", "25,95 EUR", R.drawable.momjean, ""));

        //Verifica si el context es adequat per al listener
        if (getActivity() instanceof OnProductClickListener) {
            ProductAdapter adapter = new ProductAdapter(productList, (OnProductClickListener) getActivity(), R.layout.item_product);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}

