package fr.aston.guide.ui.listing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.aston.guide.R;
import fr.aston.guide.models.hotel.Hotel;
import fr.aston.guide.models.hotel.Records;

public class HotelAdapter extends ArrayAdapter<Records> {
    private  int resId;


    public HotelAdapter( Context context, int resource, List<Records> objects) {
        super(context, resource, objects);
        resId = resource; //R.layout.item_restaurant
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //R.layout.item_restaurant
        convertView = LayoutInflater.from(getContext()).inflate(resId, null);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);

        Records item = getItem(position);

        textViewTitle.setText(item.fields.nom);
        textViewCategory.setText(item.fields.typologie+" " +item.fields.categorie);

        return convertView;

    }
}
