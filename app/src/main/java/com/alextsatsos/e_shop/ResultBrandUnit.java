package com.alextsatsos.e_shop;
//Δημιουργούμε έναν τύπο επιστροφής για αποτελέσματα , οπου το πέδιο RBUnitl αντιστοιχεί στα Unit απο το πίνακα product
// και RBBrand αντιστοιχεί στις brand απο το πίνακα product
public class ResultBrandUnit {
    private int RBUnitl;
    private  String RBBrand;

    public int getRBUnitl() {
        return RBUnitl;
    }

    public void setRBUnitl(int RBUnitl) {
        this.RBUnitl = RBUnitl;
    }

    public String getRBBrand() {
        return RBBrand;
    }

    public void setRBBrand(String RBBrand) {
        this.RBBrand = RBBrand;
    }
}
