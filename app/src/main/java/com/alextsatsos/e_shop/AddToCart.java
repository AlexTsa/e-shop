package com.alextsatsos.e_shop;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Δημιουργία πίνακα στην βάση με όνομα addTocard και πεδία: aid που ειναι το id του πίνακα addtocard και αυξάνεται αυτόματα κατα ένα ,
// pid που είναι το id των προιόντων(δεν το δηλώνουμε σαν ξένο κλειδί)άπλα παίρνουμε την τιμη και  unitsaddtocard που είναι η ποσότητα απο το προιόν που κάνει παραγγελία ο χρήστης
@Entity(tableName = "addtocard")
public class AddToCart {
@PrimaryKey(autoGenerate = true)
 private int aid;

private int pid;

private int unitsaddtocard;

    public int getUnitsaddtocard() {
        return unitsaddtocard;
    }

    public void setUnitsaddtocard(int unitsaddtocard) {
        this.unitsaddtocard = unitsaddtocard;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
