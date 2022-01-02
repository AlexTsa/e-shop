package com.alextsatsos.e_shop;
//Δημιουργούμε έναν τύπο επιστροφής για αποτελέσματα , οπου το πέδιο  RTid  αντιστοιχεί στην στήλη id απο τον πίνακα product
// RTbrand αντιστοιχεί στην στήλη brand ,RTcategory αντιστοιχεί στην στήλη category  απο τον πίνακα product,RTsalesunits αντιστοιχεί στην στήλη unitssales απο τον πίνακα sales
public class ResultTotalSalesPerProducts {
    private int RTid;
    private String RTbrand;
    private String RTcategory;
    private int RTsalesunits;

    public int getRTid() {
        return RTid;
    }

    public void setRTid(int RTid) {
        this.RTid = RTid;
    }

    public String getRTbrand() {
        return RTbrand;
    }

    public void setRTbrand(String RTbrand) {
        this.RTbrand = RTbrand;
    }

    public String getRTcategory() {
        return RTcategory;
    }

    public void setRTcategory(String RTcategory) {
        this.RTcategory = RTcategory;
    }

    public int getRTsalesunits() {
        return RTsalesunits;
    }

    public void setRTsalesunits(int RTsalesunits) {
        this.RTsalesunits = RTsalesunits;
    }
}
