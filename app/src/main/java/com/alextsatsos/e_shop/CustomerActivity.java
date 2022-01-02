package com.alextsatsos.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
EditText emailText;
EditText firstNameText;
EditText lastnameText;
EditText cityText;
EditText addressText;
EditText idCustomer;
List<AddToCart> addToCartsListcustomers;
Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        //αντιστοιχήσει με στοιχειά που βρίσκονται στο activity_customer.xml
        emailText = findViewById(R.id.customerEmaiEditTextView);
        firstNameText = findViewById(R.id.customerFistNameEditTextView);
        lastnameText = findViewById(R.id.customerLastNameEditTextView);
        cityText = findViewById(R.id.customerCityEditTextView);
        addressText = findViewById(R.id.customerAddressEditTextView);
        idCustomer = findViewById(R.id.customerIdEditTextView);

    }

    public void insetCustomersBtn(View view) {
        int idc=0;
        try{
             idc = Integer.parseInt(idCustomer.getText().toString()); // παίρνουμε το id απο EditText και το κάνουμε cast σε μια int μεταβλητή
        }catch (NumberFormatException ex){

        }

        addToCartsListcustomers = MainActivity.eshopDatabase.eshopDao().getAllTheProductFromAddToCart();  //δημιουργία μιας λίστας με όλα τα προιόντα απο το καλάθι αγορών
       //δημιουργία αντικειμένου της τάξης Customer
        customer = new Customer();
        //καλούμε τις κατάλληλες set μεθόδους και καταχωρούμε τα δεδομένα
        customer.setEmail(emailText.getText().toString());
        customer.setFirstName(firstNameText.getText().toString());
        customer.setLastName(lastnameText.getText().toString());
        customer.setCity(cityText.getText().toString());
        customer.setAddress(addressText.getText().toString());
        customer.setCid(idc);
        MainActivity.eshopDatabase.eshopDao().insertCustomer(customer);  //Εισαγωγή Customer με την μέθοδο insertCustomer
        Toast.makeText(this, " Customer Inserted, check your email for your order ", Toast.LENGTH_SHORT).show();
        maketheorder(); // κάλουμε την maketheorder()
    }
    public void maketheorder(){
        int id = customer.getCid();  // παίρνουμε το id του Customer
        Sales sales = new Sales();  //δημιουργία αντικειμένου της τάξης Sales
        Product productUpdate = new Product(); //δημιουργία αντικειμένου της τάξης Product
        for(AddToCart i: addToCartsListcustomers){
            //Δημιουργια μιας λίστας όπου πέρνουμε απο την λιστα  addToCartsListcustomers για το στοιχείο i το Pid και γεμίσουμε την λίστα με το συγκεκριμένο προιόν
            List<Product> product = MainActivity.eshopDatabase.eshopDao().getDetailsForEachProduct(i.getPid());
            for(Product j:product){
               //καλούμε τις κατάλληλες set μεθόδους και βάζουμε στο αντικείμενο sales για id του product το id , για Cid του customer το id
                // , για units τα units που έχει το κάλαθι αγορών και για Saledate την τωρινή ημερομηνία σε μόρφη ημέρας/μήνας/χρονολογία
                sales.setId(j.getId());
                sales.setCid(customer.getCid());
                sales.setUnitssales(i.getUnitsaddtocard());
                sales.setSaledate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                MainActivity.eshopDatabase.eshopDao().insertSale(sales);  //κάνουμε εισαγωγή του αντικειμένου sales

                //αφαιρούμε τις ποσοτητες για κάθε προιόν για το οποίο εγίνε παραγγελία απο τον πίνακα product και κάνουμε update το προιόν .
                productUpdate.setId(j.getId());
                productUpdate.setFeature(j.getFeature());
                productUpdate.setImageUrl(j.getImageUrl());
                productUpdate.setPrice(j.getPrice());
                productUpdate.setCategory(j.getCategory());
                productUpdate.setBrand(j.getBrand());
                productUpdate.setUnit(j.getUnit()-i.getUnitsaddtocard());
            }
            MainActivity.eshopDatabase.eshopDao().updateProduct(productUpdate); // κάλουμε την updateProduct()
        }
        deletefromaddtocart();
    }
    //Διαγράφουμε κάθε προιον που ειναι στο καλάθι αγορών καλώντας την deleteAddtoCart()
    public void deletefromaddtocart(){
        for(AddToCart i:addToCartsListcustomers){
            AddToCart addToCart = new AddToCart();
            addToCart.setAid(i.getAid());
            MainActivity.eshopDatabase.eshopDao().deleteAddtoCart(addToCart);
        }
        Intent intent = new Intent(this,MainActivity.class); // άφου γίνει διαγραφή των προιόντων απο το καλάθι με την intent πηγαίνουμε στην MainActivity
        startActivity(intent);
    }
    }


