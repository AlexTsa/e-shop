package com.alextsatsos.e_shop;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private String imageUrl[];  //χρησιμοποιούμε ενα String πίνακα για να κρατάμε το urls των εικόνων  απο το προιόντα
    private String brand[];    //χρησιμοποιούμε ενα String πίνακα για να κρατάμε τις μαρκερς απο το προιόντα
    private Listener listener;  // δημιουργούμε μια Listener μεταβλητή

    interface Listener {   // προσθέτουμε το interface
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        // ο recycler view χρειαζέται να εμφανίζει CardView , έτσι ορίζουμε οτι ο ViewHolder περιέχει CardView
        public ViewHolder(CardView v) {
            super(v);   // καλούμε super(v) καθώς περιέχει metadata.
            cardView = v;
        }
    }

    //περνάμε το δεδομένα στον adapter χρησιμοποιόντας τον δομητή
    public ImageAdapter(String[] imageUrl, String[] brand) {
        this.imageUrl = imageUrl;
        this.brand = brand;
    }

    @Override
    public int getItemCount() {
        return brand.length;   // το μήκος του πίνακα brand ισούται με τον αριθμό απο τα δεδομένα στον recyclerView
    }

    //η μέθοδος onCreateViewHolder  καλείται όταν o recycler view χρειάζεται να δημιουργήσει ένα view holder
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())  // παιρνούμε ένα LayoutInflater αντικείμενο
                .inflate(R.layout.card_image, parent, false); // ορίζουμε ποιο layout χρησιμοποιούμε για τα περιεχόμενα του viewholder
        // χρησιμοποιούμε την LayoutInflator για να μετατρέψουμε το layout μεσα στο CardView
        return new ViewHolder(cv);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    // o recycler View καλεί την μέθοδο onBindViewHolder οταν χρειάζεται να χρησιμοποιήσει το view holder για τα νέα δεδομένα
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CardView cardView = holder.cardView;
        //αντιστοιχήσει με στοιχειά που βρίσκονται card_image.xml
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        final ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);

        Picasso.get().load(imageUrl[position]).into(imageView); // η Picasso μας επιτρέπει να φορτώσουμε σε ενα imageView εικόνες απο url

        textView.setText(brand[position]);   //εμφανιζούμε την μάρκα απο το προιόν στο TextView
        cardView.setOnClickListener(new View.OnClickListener() {  // προσθέτουμε το listener στο cardView
            @Override
            public void onClick(View v) {  // οταν το CardView πατηθεί, καλούμε του listener την  onClick() μέθοδο
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }
}
