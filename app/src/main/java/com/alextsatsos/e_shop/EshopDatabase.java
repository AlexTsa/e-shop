package com.alextsatsos.e_shop;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//Δημιουργία της βάσης δεδομενών με 4 πίνακες (products,addtocart,customers,sales)
@Database(entities = {Product.class,AddToCart.class,Customer.class,Sales.class},version = 1)
public abstract class EshopDatabase extends RoomDatabase {
    public  abstract EshopDao eshopDao();   //Δήλωση του interface για να έχουμε πρόσβαση στην βάση

}
