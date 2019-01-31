package fr.aston.guide.ui.listing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fr.aston.guide.R;
import fr.aston.guide.models.hotel.Hotel;
import fr.aston.guide.models.restaurant.Restaurant;
import fr.aston.guide.utils.Constant;
import fr.aston.guide.utils.FastDialog;
import fr.aston.guide.utils.Network;

public class ListingActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private ListView listViewData;

    private List<Restaurant> restaurantList;
    private List<Hotel> hotelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewData = findViewById(R.id.listViewData);


        if (getIntent().getExtras() != null) {
            //boolean isRestaurant = getIntent().getExtras().getBoolean("isRestaurant");
            boolean isRestaurant = getIntent().getBooleanExtra("isRestaurant", false);

            if (isRestaurant) {
                textViewTitle.setText(getString(R.string.textViewTitleResto));
                // TODO: afficher les restaurants et la liste des restaurants
                restaurantList = new ArrayList<>();
                restaurantList.add(new Restaurant("Mac do", "Fast Food", "Macdo@gmail.fr","01 02 03 05 05","https://www.mcdonalds.fr/","https://upload.wikimedia.org/wikipedia/fr/thumb/e/ea/Mcdonalds_France_2009_logo.svg/1138px-Mcdonalds_France_2009_logo.svg.png"));
                restaurantList.add(new Restaurant("Shake shack", "Fast Food", "ShakeShack@gmail.fr","01 02 03 05 05","https://www.shakeshack.com/","data:image/png;base64,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"));
                restaurantList.add(new Restaurant("Amarine", "Restaurant", "Amarine@gmail.fr","01 02 03 05 05","http://www.amarine.fr/","data:image/jpeg;base64,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"));

                listViewData.setAdapter(new  RestaurantAdapter(ListingActivity.this,R.layout.item_restaurant,restaurantList));


            } else {
                if (Network.isNetworkAvailable(getApplicationContext())) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    String url_hotels = Constant.URL_HOTELS;
                    textViewTitle.setText(getString(R.string.textViewTitleHotel));

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url_hotels, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);

                            getData(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("response", "error");
                            String json = new String(error.networkResponse.data);
                            getData(json);
                        }
                    });
                    queue.add(stringRequest);
                } else {
                    FastDialog.showDialog(
                            getApplicationContext(),
                            FastDialog.SIMPLE_DIALOG
                            ,getString(R.string.dialog_error_connexion_api_hotel));
                }
            }
        }
    }

    private void getData(String response) {
        Hotel psh = new Gson().fromJson(response, Hotel.class);
        hotelList = new ArrayList<>();
        hotelList.add(new Hotel());
        listViewData.setAdapter(new HotelAdapter(ListingActivity.this,R.layout.item_restaurant,psh.records));









    }
}
