package com.alextsatsos.e_shop;
//Δημιουργούμε έναν τύπο επιστροφής για αποτελέσματα , οπου το πέδιο brandResult  αντιστοιχεί στις brand απο τον πίνακα product
//  imageUrlResult αντιστοιχεί στην στήλη imageUrl και productid αντιστοιχεί στην στήλη id  απο τον πίνακα product
public class ResultProductBrandImage {
    String brandResult;
    String imageUrlResult;
    int productid;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getBrandResult() {
        return brandResult;
    }

    public void setBrandResult(String brandResult) {
        this.brandResult = brandResult;
    }

    public String getImageUrlResult() {
        return imageUrlResult;
    }

    public void setImageUrlResult(String imageUrlResult) {
        this.imageUrlResult = imageUrlResult;
    }
}
