package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by scrutch on 14/10/15.
 */
public class CustomAdapter extends ArrayAdapter<Sensor> {
    private final Context context;
    //private final Sensor[] values;
    private final List<Sensor> values;

    public CustomAdapter(Context context, List<Sensor> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position).getName());
        return rowView;
    }




}
