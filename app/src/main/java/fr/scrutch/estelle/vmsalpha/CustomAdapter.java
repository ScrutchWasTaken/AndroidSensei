package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by scrutch on 14/10/15.
 */
public class CustomAdapter extends ArrayAdapter<Sensor> {
    private final Context context;
    //private final Sensor[] values;
    private final List<Sensor> values;
    private boolean[] checkBoxState;


    public CustomAdapter(Context context, List<Sensor> values) {
//        super(context, R.layout.rowlayout, values);
        super(context, android.R.layout.simple_list_item_checked, values);
        this.context = context;
        this.values = values;
//      android.R.layout.simple_list_item_checked
        checkBoxState = new boolean[values.size()];
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(android.R.layout.simple_list_item_checked, parent, false);
        TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position).getName());
//        final Sensor item = values.get(position);

//        CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.checkbox); //COMMENT OR NOT? YES if using xml for click

        return rowView;
    }


    //////////////////////////////////On met la méthode onClick dans le xml et on peut choper des trucs grâce à la vue (view) en paramètre
// layout horizontal pour avoir les cb et le text en joli
//    Choper le contener (parent) presque le même context que le click item listener
    //mettre des infos dans le xml pour que l'événement descende ou monte dans la hiérarchie des vues
    /////////////////////////////////click droit generate => getter, setter...



}
