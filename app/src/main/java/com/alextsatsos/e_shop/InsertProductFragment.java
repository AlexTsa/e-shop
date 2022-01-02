package com.alextsatsos.e_shop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsertProductFragment extends Fragment {
    //ορισμός EditText ,Βutton,μιας μεταβλητή double και μιας int
    private EditText brandEditTextView;
    private EditText priceTextView;
    private EditText unitsEditTextView;
    private EditText imageUrlEditTextView;
    private EditText categoryEditTextView;
    private EditText featuresEditTextView;
    private Button insertBtn;
    private Double price;
    private int units;


    public InsertProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_insert_product, container, false); //Δημιουργία του view

        //αντιστοιχήσει με στοιχειά που βρίσκονται fragment_insert_product.xml
        brandEditTextView = view.findViewById(R.id.idBrandEditTextView);
        priceTextView = view.findViewById(R.id.idPriceEditTexiView);
        unitsEditTextView = view.findViewById(R.id.idUnitsEditTextView);
        imageUrlEditTextView = view.findViewById(R.id.idImageUrlEditTextView);
        categoryEditTextView = view.findViewById(R.id.idCategoryEditTextView);
        featuresEditTextView = view.findViewById(R.id.idFeatureEditTextView);
        insertBtn = view.findViewById(R.id.idBtnInsert);
        //Δημιουργία του Click listener γιa το κουμπλι insertBtn
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Διαβάζουμε το περιεχόμενο το priceTextView και το κανούμε cast σε double
                    price = Double.parseDouble(priceTextView.getText().toString());
                    //Διαβάζουμε το περιεχόμενο το unitsEditTextView και το κανούμε cast σε integer
                    units = Integer.parseInt(unitsEditTextView.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Exception " + ex);
                }
                //Δημιουργία αντικειμένου Product
                Product product = new Product();
                 //χρησιμοποιώντας τις set μεθόδους καταχωρούμε δεδομένα
                product.setBrand(brandEditTextView.getText().toString());
                product.setCategory(categoryEditTextView.getText().toString());
                product.setUnit(units);
                product.setPrice(price);
                product.setImageUrl(imageUrlEditTextView.getText().toString());
                product.setFeature(featuresEditTextView.getText().toString());

                MainActivity.eshopDatabase.eshopDao().addProduct(product); // καταχώρηση του προιόντος στο πινακα products με την μέθοδο addProduct()
                Toast.makeText(getActivity(), "Product Added ", Toast.LENGTH_SHORT).show(); // εμφανίσει μηνύματος τυπου τοαστ
            }
        });

        return view;
    }

}
