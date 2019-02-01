package fr.aston.guide.ui.Fiche;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import fr.aston.guide.R;
import fr.aston.guide.models.hotel.Fields;

public class FicheHotelActivity extends AppCompatActivity {
    private TextView titleFicheHotel;
    private TextView categoryFicheHotel;
    private Button buttonEmailFicheHotel;
    private Button buttonPhoneFicheHotel;
    private Button buttonWebFicheHotel;
    private MapView mapbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibWljazU5MTEyIiwiYSI6ImNqcmFzZnk4ZTBzd2Q0Ym1sOTZrNDNvdXoifQ.JhlyRo8oTy5j_ffhFhZjlA");
        setContentView(R.layout.activity_fiche_hotel);

        titleFicheHotel = findViewById(R.id.TextViewFicheNameHotel);
        categoryFicheHotel = findViewById(R.id.TextViewFicheCategoryHotel);
        buttonEmailFicheHotel = findViewById(R.id.ButtonEmailFicheHotel);
        buttonPhoneFicheHotel = findViewById(R.id.ButtonPhoneFicheHotel);
        buttonWebFicheHotel = findViewById(R.id.ButtonWebFicheHotel);
        mapbox = findViewById(R.id.MapViewFicheHotel);

        if (getIntent().getExtras() != null) {
            final Fields hotel = (Fields) getIntent().getExtras().get("hotel");

            titleFicheHotel.setText(hotel.nom);
            categoryFicheHotel.setText(hotel.categorie);
            buttonEmailFicheHotel.setText(hotel.adresse);
            buttonPhoneFicheHotel.setText(hotel.commune);
            buttonWebFicheHotel.setText(hotel.capacite_personne);

            mapbox.onCreate(savedInstanceState);
            mapbox.getMapAsync(new OnMapReadyCallback() {
                                   @Override
                                   public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                                       mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {

                                           @Override
                                           public void onStyleLoaded(@NonNull Style style) {
                                               // Add the marker image to map
                                               style.addImage("marker-icon-id",
                                                       BitmapFactory.decodeResource(
                                                               FicheHotelActivity.this.getResources(), R.drawable.mapbox_marker_icon_default));

                                               double lat = hotel.coord_geo_adresse[0];
                                               double lng = hotel.coord_geo_adresse[1];

                                               LatLng pos = new LatLng( lat ,lng);
                                               mapboxMap.animateCamera(
                                                       CameraUpdateFactory.newLatLng(pos),
                                                       500);

                                               GeoJsonSource geoJsonSource = new GeoJsonSource("source-id", Feature.fromGeometry(
                                                       Point.fromLngLat(lng, lat)));
                                               style.addSource(geoJsonSource);

                                               SymbolLayer symbolLayer = new SymbolLayer("layer-id", "source-id");
                                               symbolLayer.withProperties(
                                                       PropertyFactory.iconImage("marker-icon-id")
                                               );
                                               style.addLayer(symbolLayer);
                                           }
                                       });
                                   }
                               });

                    Log.e("hotel", hotel.nom);


        }
    }

    // Add the mapView's own lifecycle methods to the activity's lifecycle methods
    @Override
    public void onStart() {
        super.onStart();
        mapbox.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapbox.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapbox.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapbox.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapbox.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapbox.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapbox.onSaveInstanceState(outState);
    }

}
