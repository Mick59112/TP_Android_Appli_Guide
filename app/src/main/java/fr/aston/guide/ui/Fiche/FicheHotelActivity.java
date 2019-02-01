package fr.aston.guide.ui.Fiche;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import fr.aston.guide.R;
import fr.aston.guide.models.hotel.Fields;

public class FicheHotelActivity extends AppCompatActivity {
    private TextView titleFicheHotel;
    private TextView categoryFicheHotel;
    private Button buttonEmailFicheHotel;
    private Button buttonPhoneFicheHotel;
    private Button buttonWebFicheHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_hotel);

        titleFicheHotel = findViewById(R.id.TextViewFicheNameHotel);
        categoryFicheHotel = findViewById(R.id.TextViewFicheCategoryHotel);
        buttonEmailFicheHotel = findViewById(R.id.ButtonEmailFicheHotel);
        buttonPhoneFicheHotel = findViewById(R.id.ButtonPhoneFicheHotel);
        buttonWebFicheHotel = findViewById(R.id.ButtonWebFicheHotel);

        if (getIntent().getExtras() != null) {
            Fields hotel = (Fields) getIntent().getExtras().get("hotel");

            Log.e("hotel", hotel.nom);

            titleFicheHotel.setText(hotel.nom);
            categoryFicheHotel.setText(hotel.categorie);
            buttonEmailFicheHotel.setText(hotel.adresse);
            buttonPhoneFicheHotel.setText(hotel.commune);
            buttonWebFicheHotel.setText(hotel.capacite_personne);
        }
    }
}
