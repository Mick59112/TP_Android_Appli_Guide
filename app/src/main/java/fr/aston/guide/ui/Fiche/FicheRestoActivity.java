package fr.aston.guide.ui.Fiche;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.aston.guide.R;
import fr.aston.guide.models.restaurant.Restaurant;

public class FicheRestoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageFicheResto;
    private TextView titleFicheResto;
    private TextView categoryFicheResto;
    private Button buttonEmailFicheResto;
    private Button buttonPhoneFicheResto;
    private Button buttonWebFicheResto;

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        switch (v.getId()) {
            case R.id.ButtonEmailFicheResto:
                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{button.getText().toString()});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "le sujet");
                intentEmail.putExtra(Intent.EXTRA_TEXT, "le message");
                startActivity(Intent.createChooser(intentEmail, "message"));
                break;
            case R.id.ButtonPhoneFicheResto:
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:" +button.getText().toString()));
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED
                ) {
                    // Permission is not granted
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 123);
                    }
                    return;
                }
                startActivity(intentPhone);
                break;
            case R.id.ButtonWebFicheResto:
                Intent intentURL = new Intent(Intent.ACTION_VIEW);
                intentURL.setData(Uri.parse("http://" + button.getText().toString()));
                startActivity(intentURL);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            if (permissions[0].equals(Manifest.permission.CALL_PHONE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buttonPhoneFicheResto.performClick();
            }
        }
    }

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

        buttonEmailFicheResto.setOnClickListener(this);
        buttonPhoneFicheResto.setOnClickListener(this);
        buttonWebFicheResto.setOnClickListener(this);

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
