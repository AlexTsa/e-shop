package com.alextsatsos.e_shop;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

//κάνουμε implement OnNavigationItemSelectedListener  για να μπορεί η activity να ανταποκρίνεται οταν ο χρήστης κάνει click στις επιλογες του navigation drawer
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static EshopDatabase eshopDatabase; //ορισμός αντικειμένου  eshopDatabase για να έχουμε πρόσβαση στην βάση

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   //κανούμε set την toolbar ως της activity την appbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Προσθέτουμε το burger icon  με παράμετρο την τρέχων activity , to DrawerLayout, της activity την toolbar , και κάποια String που είναι χρήσιμα για την προσβασιμότητα που τα έχουμε προσθέση στο resource file string.xml
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle); // αφου δημιουργήσουμε το toggle το προσθέτουμε στο drawer καλώντας τη μέθοδο addDrawerListener()
        toggle.syncState();   //καλούμε την μέθοδο syncState για να συγχρονισούμε την εικονά στην toolbar με την κατάσταση του drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); //εδώ εγγράφουμε την activity ως listener για το navigation view ώστε να ειδοποιηθεί στην περιπτώτη του click σε ενα αντικείμενο
          //Δημιουργία του αντικειμένου eshopDatabase με την κλάση room με την μέθοδο databaseBuilder που έχει σαν παράμετρο getApplicationContext() για την ανάπτυξη στην διεπαφή,
        //EshopDatabase που είναι η κλάση που έχει αναπτυχθεί η βάση, και το όνομα της βάσης που ειναι το eshopDb.Επίσης επιτρέπουμε η βάση να τρέχει στο main thread
        eshopDatabase = Room.databaseBuilder(getApplicationContext(), EshopDatabase.class, "eshopDb").allowMainThreadQueries().build();
        Fragment fragment = new DisplayProductsFragment();   //χρησιμοποιούμε fragment Transaction για την εμφανίσουμε ενα στιγμιότυπο του DisplayProductsFragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction(); // κάνουμε start την transaction
        ft.add(R.id.content_frame, fragment);  // προσθέτου το fragment  στο ViewGroup
        ft.commit();//κάνουμε commit την transaction
    }
    //η μεθόδος onNavigationItemSelected καλείται όταν ο χρήστης κάνει click σε ενα αντικείμενο στο drawer
    //παίρνει μια παράμετρο το Menuitem που πατήθηκε
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();  // παίρνουμε το id απο το αντικείμενο που έχει επιλεγθεί
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_insertProduct:
                fragment = new InsertProductFragment(); //κρατάμε ενα αντικείμενο απο το fragment που θέλουμε να εμφανιστεί στην fragment μεταβλητή*
                break;
            case R.id.nav_Queries:
                fragment = new QueriesFragment();  //*
                break;
            default:  //Εμφανίζουμε το DisplayProductsFragment ως προπροεπιλεγμένο
                fragment = new DisplayProductsFragment();
        }
        if (fragment != null) { // χρησιμοποιούμε fragment Transaction για εμφανισούμε το fragment
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// κάνουμε start την transaction
            ft.replace(R.id.content_frame, fragment); // κάθε φόρα θα γίνεται αντικατάσταση του fragment με νέο αντικείμενο απο το fragment
            ft.commit();  // κάνουμε commit την transaction
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START); //κλείνουμε το drawer όταν ο χρήστης επιλέξει μια επιλογή
        return true;

    }
    //ή μέθοδος onBackPressed() καλείται οταν back button πατηθεί
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {  //εαν το drawer ειναι ανοιχτό -->κλείστο
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();  //αλλιώς καλούμε τη super.onBackPressed();
        }
    }
}

