package com.alextsatsos.e_shop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//Δημιουργία ένος Data Access Object για να έχουμε με τις μεθόδους πρόσβαση στην βάση
@Dao
public interface EshopDao {

    @Insert
    public void addProduct(Product product);  // η μέθοδος addProduct καταχωρεί αντικείμενα της Class Product

    @Insert
    public void addToCardInsert(AddToCart addToCart); // η μέθοδος  addToCardInsert καταχωρεί αντικείμενα της Class AddToCart

    @Insert
    public void insertCustomer(Customer customer); // η μέθοδος  insertCustomer καταχωρεί αντικείμενα της Class Customer

    @Insert
    public void insertSale(Sales sales); // η μέθοδος insertSale καταχωρεί αντικείμενα της Class Sales

    @Delete
    public void deleteProduct(Product product); // η μέθοδος διαγράφει προιόντα

    @Delete
    public void deleteCustumer(Customer customer);// η μέθοδος διαγράφει πέλατες

    @Delete
    public void deleteSales(Sales sales);// η μέθοδος διαγράφει πώλησεις

    @Delete
    public  void deleteAddtoCart(AddToCart addToCart);// η μέθοδος διαγράφει προιόντα απο το καλαθί προιόντων
    @Update
    public void updateProduct(Product product);// η μέθοδος κάνει update τον πίνακα products

    @Update
    public  void updateCustomer(Customer customer);// η μέθοδος  κάνει update τον πίνακα customers

    @Update
    public void updateSales(Sales sales);// η μέθοδος κάνει update τον πίνακα sales

    @Query("select * from products")
    public List<Product> getAllProducts();// η μέθοδος αύτη μας γύρναει μια λίστα με όλα τα προιόντα

    @Query("select * from addtocard")// η μέθοδος αύτη μας γύρναει μια λίστα με όλα τα προιόντα απο τα καλάθι προιόντων
    public  List<AddToCart> getAlladdTocart();

    @Query("select * from customers")// η μέθοδος αύτη μας γύρναει μια λίστα με όλους τους πελάτες
    public List<Customer> getAllCustomers();

    @Query("select * from sales")// η μέθοδος αύτη μας γύρναει μια λίστα με όλες τις πωλήσεις
    public List<Sales> getAllSales();

    @Query("select brand as brandResult,imageUrl as imageUrlResult,id as productid  from products")
    public List<ResultProductBrandImage> getProductsBrandImage();// η μέθοδος αύτη γεμίζει μια λίστα με τις μάρκες , το id, και url για τις εικόνες

    @Query("select * from products where id= :inputId")
    public List<Product> getDetailsForEachProduct(int inputId);//η μέθοδος αύτη γεμίζει μια λίστα με το προιόν με το συγκεκριμένο id το οποίο το περνάμε δυναμίκα σαν παράμετρο

    @Query("select * from addtocard")//η μέθοδος αύτη μας γύρναει μια λίστα με όλα τα προιόντα απο τα καλάθι προιόντων
    public List<AddToCart> getAllTheProductFromAddToCart();

    @Query("select brand as RBBrand  ,unit as RBUnitl  from products Group BY brand ")// η μέθοδος αυτη μας γεμίζει μια λίστα με τις μαρκες και τις ποσότητες για τα προιόντα και κάνουμε group by στην brand στήλη
    public List<ResultBrandUnit> getunitperbrand();

    @Query("select SUM(S.unitssales) from sales S inner join products P On S.sid =P.id ")// η μέθοδος αυτή μας γύρναει μια λίστα με τις συνολικές πωλήσεις
    List<Integer> getunitssales();

    @Query("select P.id as RTid, P.brand as RTbrand,P.category as RTcategory ,SUM(S.unitssales) as RTsalesunits from products P inner join sales S On S.sid=P.id Group BY p.id")
     List<ResultTotalSalesPerProducts> getResultTotalSalesPerProducts();// η μέθοδος γεμίζει μια λίστα  που έχει τις πωλήσεις ανά προϊόν
}
