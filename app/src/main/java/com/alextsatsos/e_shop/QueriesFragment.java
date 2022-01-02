package com.alextsatsos.e_shop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueriesFragment extends Fragment {
    ArrayAdapter<CharSequence> arrayAdapter;
    int pos;

    public QueriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_queries, container, false); //Δημιουργία του view
        //αντιστοιχήσει με στοιχειά που βρίσκονται fragment_queries.xml
        Button runquery = (Button) view.findViewById(R.id.runQueries);
        final TextView queryTextView = view.findViewById(R.id.QueriesTextView);
        final TextView queryDetailsText = view.findViewById(R.id.QueriesTextViewdetails);
        final String arraywithqueriesdetails[] = getResources().getStringArray(R.array.string_array_queries);
        Spinner spinner = view.findViewById(R.id.spinnerqueries);
        //δημιουργία ένος arrayadapter με τίμες απο resource file string.xml
        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter); //σύνδεση του spinner με τον adapter
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //ορίζουμε για το spinner εναν Listener
            //έτσι οταν επιλεχθεί ένα αντικείμενο στο spinner  βάζουμε queryDetailsText το κατάλληλο κείμενο και κράταμε στην μεταβλητή pos την θέση του αντικειμένου που επιλέχθηκε
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryDetailsText.setText(arraywithqueriesdetails[position]);
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        runquery.setOnClickListener(new View.OnClickListener() { // ορίζουμε για το κουμπί έναν listener
            @Override
            public void onClick(View v) {
                String q = "";
                switch (pos) { //ανάλογα με την τιμή της pos παίρνουμε περιπτώσης
                    case 0://δημιουργία μιας λίστας  που έχει το υπόλοιπο της ποσότητας ανά προϊόν
                        List<ResultBrandUnit> resultBrandUnits = MainActivity.eshopDatabase.eshopDao().getunitperbrand();
                        for (ResultBrandUnit i : resultBrandUnits) {
                            q = q + " Brand : " + i.getRBBrand() + " Unit: " + i.getRBUnitl() + " \n";
                        }
                        queryTextView.setText(q);
                        break;
                    case 1://δημιουργία μιας λίστας  που έχει τις συνολικές πωλήσεις
                        List<Integer> integers = MainActivity.eshopDatabase.eshopDao().getunitssales();
                        for (Integer i : integers) {
                            q = "Total Sales :" + q + i;
                        }
                        queryTextView.setText(q);
                        break;
                    case 2://δημιουργία μιας λίστας  που έχει τις πωλήσεις ανά προϊόν
                        List<ResultTotalSalesPerProducts> resultTotalSalesPerProducts = MainActivity.eshopDatabase.eshopDao().getResultTotalSalesPerProducts();
                        for (ResultTotalSalesPerProducts i : resultTotalSalesPerProducts) {
                            q = "\n " + q + " Brand : " + i.getRTbrand() + "\n" + " ID: " + i.getRTid() + "\n Total Sales: " + i.getRTsalesunits() + "\n" + " Category: " + i.getRTcategory() + "\n";
                        }
                        queryTextView.setText(q);
                        break;
                    case 3://δημιουργία μιας λίστας  που έχει την αναλυτική κατάσταση πελάτων
                        List<Customer> customers = MainActivity.eshopDatabase.eshopDao().getAllCustomers();
                        for (Customer i : customers) {
                            q = q + " Id :" + i.getCid() + "\n Address: " + i.getAddress() + "\n City: " + i.getCity() + "\n Email: " + i.getEmail() + "\n FirstName: " + i.getFirstName() + "\n LastName: " + i.getLastName() + "\n";

                        }
                        queryTextView.setText(q);
                        break;
                    case 4://δημιουργία μιας λίστας  που έχει την αναλυτική κατάσταση προϊόντων.
                        List<Product> productquery = MainActivity.eshopDatabase.eshopDao().getAllProducts();
                        for (Product i : productquery) {
                            q = q + " Id: " + i.getId() + "\n  Brand: " + i.getBrand() + "\n " + " Category: " + i.getCategory() + "\n " + " Price: " + i.getPrice() + "\n " + " Units: " + i.getUnit() + "\n " + " Features: " + i.getFeature() + "\n";
                        }
                        queryTextView.setText(q);
                        break;

                }
            }
        });
        return view;
    }


}
