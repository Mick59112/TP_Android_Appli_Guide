package fr.aston.guide.ui.Fiche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.aston.guide.R;
import fr.aston.guide.models.restaurant.Restaurant;

public class FicheActivity extends AppCompatActivity {
    private ImageView imageFicheResto;
    private TextView titleFicheResto;
    private TextView categoryFicheResto;
    private Button buttonEmailFicheResto;
    private Button buttonPhoneFicheResto;
    private Button buttonWebFicheResto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche);

        imageFicheResto = findViewById(R.id.ImageViewFicheResto);
        titleFicheResto = findViewById(R.id.TextViewFicheNameResto);
        categoryFicheResto = findViewById(R.id.TextViewFicheCategoryResto);
        buttonEmailFicheResto = findViewById(R.id.ButtonEmailFicheResto);
        buttonPhoneFicheResto = findViewById(R.id.ButtonPhoneFicheResto);
        buttonWebFicheResto = findViewById(R.id.ButtonWebFicheResto);

        if (getIntent().getExtras() != null) {
            Restaurant restaurant = (Restaurant) getIntent().getExtras().get("restaurant");

            Log.e("restaurant", restaurant.name);

            Picasso.get().load(restaurant.image).into(imageFicheResto);

            titleFicheResto.setText(restaurant.name);
            categoryFicheResto.setText(restaurant.category);
            buttonEmailFicheResto.setText(restaurant.email);
            buttonPhoneFicheResto.setText(restaurant.phone);
            buttonWebFicheResto.setText(restaurant.url);
        }
    }
}
