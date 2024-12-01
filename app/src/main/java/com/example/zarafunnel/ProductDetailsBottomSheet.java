package com.example.zarafunnel;

import android.os.Bundle;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import android.util.Log;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class ProductDetailsBottomSheet extends BottomSheetDialogFragment {
    private String productName;
    private String productPrice;
    private int productImageResId;
    private static final String TAG = "ProductDetailsBottomSheet";

    public static ProductDetailsBottomSheet newInstance(String productName, String productPrice, int productImageResId) {
        ProductDetailsBottomSheet fragment = new ProductDetailsBottomSheet();
        Bundle args = new Bundle();
        args.putString("productName", productName);
        args.putString("productPrice", productPrice);
        args.putInt("productImageResId", productImageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_product_details, container, false);
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle);

        //Recuperem les dades passades
        if (getArguments() != null) {
            productName = getArguments().getString("productName");
            productPrice = getArguments().getString("productPrice");
            productImageResId = getArguments().getInt("productImageResId");
        }

        //Configurar el botó d'afegir a la cistella'
        Button addToCartButton = view.findViewById(R.id.button);
        addToCartButton.setOnClickListener(v -> {
            //Obrir el BottomSheet de talles i passar les dades
            SizeSelectionBottomSheetFragment sizeBottomSheet = SizeSelectionBottomSheetFragment.newInstance(productName, productPrice, productImageResId);
            sizeBottomSheet.show(getParentFragmentManager(), sizeBottomSheet.getTag());
            dismiss();
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Fer que el BottomSheet s'expandeixi completament
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        if (dialog != null) {
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from((View) view.getParent());
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setPeekHeight(0);
            behavior.setHideable(false);
        }

        TextView productTitle = view.findViewById(R.id.productName);
        TextView productPriceTextView = view.findViewById(R.id.productPrice);
        ImageView productImageView = view.findViewById(R.id.productImage);
        ImageButton closeButton = view.findViewById(R.id.closeButton);

        //Establir valors a la vista
        productTitle.setText(productName);
        productPriceTextView.setText(productPrice);
        productImageView.setImageResource(productImageResId);

        closeButton.setOnClickListener(v -> dismiss());
    }
}

