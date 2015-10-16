package fr.scrutch.estelle.vmsalpha;

//Je fais tout dans une même activité parce que voilà

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private String sensorClicked;
    private String theSensorClicked;
    public final static String EXTRA_MESSAGE = "fr.scrutch.estelle.vmsalpha.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //1- choper les noms des capteurs (pour les mettre en id des boutons créés?)
        List<String> deviceSensorsName = new ArrayList<>();
        for(Sensor s : deviceSensors) {
            deviceSensorsName.add(s.getName());
        }

        final ListView listview = (ListView) findViewById(R.id.listview);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,deviceSensorsName);
//        CustomAdapter adapter = new CustomAdapter(this, deviceSensors);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sensorClicked = ((TextView)view).getText().toString();
                //theSensorClicked = (String) getListAdapter().getItem(position);
                //String sizeDeviceSensor = String.valueOf(deviceSensors.size());
                //Toast.makeText(getBaseContext(), sensorClicked, Toast.LENGTH_LONG).show(); //param: context, text,durée
                //choper le capteur et changer d'activité pour l'exploiter
                //le onItemClickListener agit comme un bouton pour le changement de vue
                goSensorClicked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goSensorClicked() {
        Intent intent = new Intent(this, SensorClickedActivity.class);
        intent.putExtra(EXTRA_MESSAGE, sensorClicked);
        startActivity(intent);
    }

    //je refais la même classe précédente mais pour quand la liste est constituée des objets
    public void goClicked() {
        Intent intent = new Intent(this, SensorClickedActivity.class);
        intent.putExtra(EXTRA_MESSAGE, theSensorClicked);
        startActivity(intent);
    }
}
