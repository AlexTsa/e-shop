package com.alextsatsos.e_shop;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayProductsFragment extends Fragment {


    public DisplayProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView eshopproductRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_display_products, container, false);  //Δημιουργούμε το RecycleView
        List<ResultProductBrandImage> products = MainActivity.eshopDatabase.eshopDao().getProductsBrandImage(); //Παιρνουμε μια λιστά που έχει τα id απο το προιόντα την μάρκα και το url για την εικόνα
        String brand[] = new String[products.size()];  // Δημιουργία ενός πίνακα που θα έχει τις μάρκες απο τα προιόντα
        String imageUrl[] = new String[products.size()];// Δημιουργία ενός πίνακα που θα έχει εικόνες url απο τα προιόντα
        final int productIds [] = new  int[products.size()];// Δημιουργία ενός πίνακα που θα έχει id απο τα προιόντα
       //Γεμίσμα των πινάκων
        for (int i = 0; i < products.size(); i++) {
            brand[i] = products.get(i).getBrandResult();
            imageUrl[i] = products.get(i).getImageUrlResult();
            productIds[i] = products.get(i).getProductid();
        }
        ImageAdapter imageAdapter = new ImageAdapter(imageUrl, brand); // περνάμε του πίνακες στον adapter
        eshopproductRecycler.setAdapter(imageAdapter);  //χρησιμοποιούμε την setAdapter για να κανούμε την σύνδεση  του imageAdapter στο RecycleView
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1); //Δημιουργία ενός GridLayoutManager που παίρνει σαν παραμετρος το Context(getActivity)  και τον αριθμό των στήλων(1 σε εμας)
        eshopproductRecycler.setLayoutManager(layoutManager); // Με αυτό το τρόπο κάθε cardView για κάθε προιόν θα εμφανίζεται σε μια στήλη
        imageAdapter.setListener(new ImageAdapter.Listener() { //κάνουμε implement το Listener  την onClick μέθοδο
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DetailEachProductActivity.class);
                intent.putExtra(DetailEachProductActivity.DetailEachProductActivityId,productIds[position]); //
                getActivity().startActivity(intent); //Ξεκίναει ή DetailEachProductActivity περνώντας στην static μεταβλήτη (DetailEachProductActivityId) το id απο το προιόν που έπελεξε ο χρήστης

            }
        });
        return eshopproductRecycler;

    }

}
