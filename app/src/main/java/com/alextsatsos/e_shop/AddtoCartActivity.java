package com.alextsatsos.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;


import java.util.List;


public class AddtoCartActivity extends AppCompatActivity {
    private double totalPriceaddtocart = 0; //Δημιουργία double μεταβλητής για την συνολική τιμή
    TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_cart);
        //αντιστοιχήσει με στοιχειά που βρίσκονται στο activity_addto_cart.xml
        totalPriceTextView = (TextView) findViewById(R.id.totalPriceTextView);
        ListView ListCardItems = (ListView) findViewById(R.id.list_cart);

        List<AddToCart> addToCartsList = MainActivity.eshopDatabase.eshopDao().getAllTheProductFromAddToCart(); //καλούμε την μέθοδο getAllTheProductFromAddToCart για την δημιουργία  μιας λίστας που περιέχει όλα τα προιόντα που είναι στο καλάθι
        String FromcartArray[] = new String[addToCartsList.size()];   //δημιουργια πινακα String
       //δημιουργούμε μια for loop
        for (int i = 0; i < FromcartArray.length; i++) {
            String details = "";
            //Δημιουργια μιας λίστας όπου πέρνουμε απο την λιστα addToCartsList για το στοιχείο i  το id του και έπειτα με την μέθοδο getDetailsForEachProduct γεμίζουμε την λίστα με το συγκεκριμένο προιόν
            List<Product> product = MainActivity.eshopDatabase.eshopDao().getDetailsForEachProduct(addToCartsList.get(i).getPid());
            for (Product j : product) {
                //προσθέτουμε στο String details το id , brand, features , price,units για ενα προιόν
                details = "Product Id: " + j.getId() + "  Brand: " + j.getBrand() + " Features: " + j.getFeature() + " Price: " + j.getPrice() + "€ Units: " + addToCartsList.get(i).getUnitsaddtocard();
                FromcartArray[i] = details;  // τοποθετούμε στο  FromcartArray πινακσ  σε καθε στοιχείο i την details μεταβλητή
                totalPriceaddtocart = totalPriceaddtocart + (j.getPrice() * addToCartsList.get(i).getUnitsaddtocard()); // ορίζουμε την συνολική τιμή
            }

        }
        totalPriceTextView.setText("Total price:" + String.valueOf(totalPriceaddtocart + "€"));  // εμφανίζουμε την συνολική τιμή
         //Δημιουργούμε ένα arrayAdapter για να τον συνδέσουμε  στην ListCardItems
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,   //η τρέχω activity
                android.R.layout.simple_list_item_1, //κάθε αντικείμενο στον arrayAdapter σε μοναδικό κείμενο
                FromcartArray);  // ο πινακας  FromcartArray

        ListCardItems.setAdapter(listAdapter); // σύνδεση με την  ListCardItems

    }

    public void continueToCustomerActivityBtn(View view) {
        Intent intent = new Intent(this, CustomerActivity.class); //ξεκινούμε μια intent πηγαίνοντας στην CustomerActivity
        startActivity(intent);
    }
}
