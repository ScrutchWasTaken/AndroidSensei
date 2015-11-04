package fr.scrutch.estelle.vmsalpha;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.SensorEvent;
import android.widget.Toast;

public class SensorClickedActivity extends AppCompatActivity implements SensorEventListener {

    //test variables
//    private long lastUpdate = System.currentTimeMillis();
    SensorManager aSensorManager;
    Sensor aSensor;
    long time1,time2,time3;
    //end test variables
    //TextView text1 = (TextView) findViewById(R.id.text1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.acceleration);


        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //get sensor by his name to catch data
        //aSensor = aSensorManager.getSensorList(Sensor.TYPE_ALL).get
        aSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensors_list, menu);
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

    ///////////////////Starting Sensors test just accelerometer, working ;)


    private void getAccelerometer(SensorEvent event) {
        TextView textView =  (TextView) findViewById(R.id.textView);
        TextView textView2 =  (TextView) findViewById(R.id.textView2);
        TextView textView3 =  (TextView) findViewById(R.id.textView3);

        time1 = System.currentTimeMillis();     //time before getting values
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];


        time2 = event.timestamp;      //time after getting values

            //Toast.makeText(this, "x:"+x+"\n"+"y:"+y+"\n"+"z:"+z, Toast.LENGTH_SHORT).show();
            //R.id.textView(Long.toString(time1));

            //the force of gravity must be eliminated => problem of the direction of the phone
//            TextView textView = new TextView(this);
//            textView.setTextSize(20);
//            textView2.setText("Accelerometer" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");

        textView.setText(Long.toString(time1));
//        setContentView(textViewTime2);
        time3 = System.currentTimeMillis();
        textView2.setText(Long.toString(time2));textView3.setText(Long.toString(time3));
        setContentView(textView);setContentView(textView2);setContentView(textView3);   //Update view


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        aSensorManager.registerListener(this,aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),1000);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        aSensorManager.unregisterListener(this);
    }


    //////////////////End test
    ////////////////////////Other sensors:
    private void getMagnetic(SensorEvent event) {
        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];



    }



    /////////////////////// End test
}
