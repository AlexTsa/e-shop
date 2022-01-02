package com.alextsatsos.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailEachProductActivity extends AppCompatActivity {

public static final String DetailEachProductActivityId="";

TextView brandTextView;
TextView priceTextView;
ImageView imageViewUrl;
TextView featureTextView;
Spinner spinner;
int productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_each_product);
        //αντιστοιχήσει με στοιχειά που βρίσκονται στο activity_detail_each_product.xml αρχείο
        brandTextView = (TextView)findViewById(R.id.brand_text);
        priceTextView = (TextView)findViewById(R.id.price_text);
        featureTextView=(TextView)findViewById(R.id.feature_text);
        imageViewUrl = (ImageView)findViewById(R.id.product_image);
        spinner =(Spinner)findViewById(R.id.unitsSpinner);

        productId = (Integer)getIntent().getExtras().get(DetailEachProductActivityId); // παίρνουμε απο την intent σε μια μεταβλητή int το id απο το προιόν που επέλεξε ο χρήστης
        List<Product> listforeachProduct= MainActivity.eshopDatabase.eshopDao().getDetailsForEachProduct(productId); //Καλούμε την μεθόδο getDetailsForEachProduct(productId) και δημιουργούμε μια λίστα όπου περιέχει τις πληροφορίες για το προιόν με το συγκεκριμένο id
      //Κάνουμε για for loop και στο brandTextView κάνουμε setText την μάρκα που την πήραμε απο την λίστα,  priceTextView βάζουμε την τιμη για το προιον,featureTextView βάζουμε τα χαρακτηριστικα.
        for(Product i:listforeachProduct){
            brandTextView.setText(" Brand: "+i.getBrand());
            priceTextView.setText("Price:  "+String.valueOf(i.getPrice())+" €");
            featureTextView.setText("Feature: "+i.getFeature());
            Picasso.get().load(i.getImageUrl()).into(imageViewUrl);  // φορτώνουμε imageView μια εικόνα απο το url με την βοήθεια της Picasso
        }

    }
    public void addtoCartddetailProduct(View view) { //Οταν πατηθεί το κουμπί AddToCart
       AddToCart addToCart = new AddToCart();  // Δημιουργούμε ένα αντικείμενο την τάξης AddTocart
       addToCart.setPid(productId); //κανουμε set το id του
       addToCart.setUnitsaddtocard(Integer.parseInt(spinner.getSelectedItem().toString())); // κάνουμε set την/τις μόναδες απο το προιόν
       MainActivity.eshopDatabase.eshopDao().addToCardInsert(addToCart); // καλούμε την μέθοδο addToCardInsert για να εισάγουμε το αντικείμενο στο πίνακα addtocart της βάσης μας
       Toast.makeText(this,"added successfully",Toast.LENGTH_SHORT).show();  // εμφάνισει μηνύματος
    }
    public void order(View view) {//Οταν πατηθεί το κουμπί order ξεκινούμε μια intent πηγαίνοντας στην AddtoCartActivity
       Intent intent = new Intent(this,AddtoCartActivity.class);
       startActivity(intent);
    }
}
