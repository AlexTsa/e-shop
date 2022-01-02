package com.alextsatsos.e_shop;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
//Δημιουργία πίνακα στην βάση με όνομα sales και πεδία sid(ειναι ξένο κλειδί και αναφέρεται στο product id),scid(ειναι ξένο κλειδί και αναφέρεται στο customer id),
// unitssale(η ποσοτητα απο το προιόν που κάνει παραγγελία ο χρήστης) και saledate που ειναι η τρέχων ημερομηνία που εγίνε η παραγγελία
@Entity(tableName = "sales", primaryKeys = {"sid", "scid", "unitssales","saledate"},
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "id",
                        childColumns = "sid", //ορισμός ξένου κλειδιου αναφέρεται στο product id
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Customer.class,
                        parentColumns = "cid",
                        childColumns = "scid", //ορισμός ξένου κλειδιου αναφέρεται στο customer id
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)
        })
public class Sales {
    @ColumnInfo(name = "sid")@NonNull
    private int id;

    @ColumnInfo(name = "scid")@NonNull
    private int cid;

    @NonNull
    private int unitssales;

    @NonNull
    private String saledate;

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public int getUnitssales() {
        return unitssales;
    }

    public void setUnitssales(int unitssales) {
        this.unitssales = unitssales;
    }

    public int getId() {
        return id;
    }

    public int getCid() {
        return cid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
