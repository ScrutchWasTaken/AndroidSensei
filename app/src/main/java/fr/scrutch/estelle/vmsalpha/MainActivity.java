package fr.scrutch.estelle.vmsalpha;


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

//Ici, c'est Scrutchy
public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor sensorClicked;
    private String theSensorClicked;
    public final static String EXTRA_MESSAGE = "fr.scrutch.estelle.vmsalpha.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the sensor manager to get the sensors'list
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //Create a list with just sensors' names (replaced by a CustomAdapter)
        //GOES WITH the ArraAdapter
//        List<String> deviceSensorsName = new ArrayList<>();
//        for(Sensor s : deviceSensors) {
//            deviceSensorsName.add(s.getName());
//        }

        final ListView listview = (ListView) findViewById(R.id.listview);

        //Create the adapter that will be shown (ArrayAdater goes with the deviceSensorsName above)
        //The 2nd will be the one we use but the "ontiemclick" function doesn't work yet
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,deviceSensorsName);
        CustomAdapter adapter = new CustomAdapter(this, deviceSensors);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //This function changes the activity (selecting the sensor)

                //Create the object showed
                //sensorClicked = ((TextView)view).getText().toString();
                sensorClicked = (Sensor)parent.getItemAtPosition(position);
                theSensorClicked = parent.getItemAtPosition(position).toString();
                //Toast is to show a variable in a little window that appears and disapperas quite quickly
                //Toast.makeText(getBaseContext(), sensorClicked, Toast.LENGTH_LONG).show(); //parameters: context, text, time shown
                //Next is the function to change the view (with the name of the sensor (1st) or the object (2nd)
                goSensorClicked();
                //goClicked();
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

    //Change the Activity but doesn't get the sensor yet
    public void goSensorClicked() {
        Intent intent = new Intent(this, SensorClickedActivity.class);
        intent.putExtra(EXTRA_MESSAGE, sensorClicked.getName());
        //intent.putExtra(EXTRA_MESSAGE, theSensorClicked);
        startActivity(intent);
    }

}
