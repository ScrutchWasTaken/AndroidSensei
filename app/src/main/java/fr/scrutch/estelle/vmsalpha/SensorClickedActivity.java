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

    int count = 0;
    SensorManager aSensorManager;
    Sensor aSensor;
    long time1,time2,time3;
    int sensorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.acceleration);


        Intent intent = getIntent();

//        sensorType = intent.getStringExtra("sensorType");
        sensorType = intent.getExtras().getInt("sensorType");
        System.out.println(sensorType);
        //get sensor by his name to catch data
//        aSensor = (Sensor) message;
//        {Sensor name="AK8963 3-axis Magnetic field sensor", vendor="Asahi Kasei", version=1, type=2, maxRange=2000.0, resolution=0.0625, power=6.8, minDelay=20000}
//        resolution will be important => help user to chose the right sensor
//        System.out.println(Sensor.TYPE_MAGNETIC_FIELD); // => 2
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER: getAccelerometer(event); break;
            case Sensor.TYPE_MAGNETIC_FIELD: getMagnetic(event); break;
            default: getSensorType(event);
        }
//        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
//            getAccelerometer(event);
//        }
//        else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
//            getMagnetic(event);
//        }
//        else{
//            getSensorType(event);
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
        //en avoir plein => correspond au nbr de sensors selected
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        aSensorManager.unregisterListener(this);
    }


    ////////////////////////Other sensors:
    private void getAccelerometer(SensorEvent event) {
        count = (count+1)%300;
        TextView textView =  (TextView) findViewById(R.id.textView);
        TextView textView2 =  (TextView) findViewById(R.id.textView2);
        TextView textView3 =  (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        if(count==0)
            time1 = System.nanoTime();     //time before getting values
//        time1 = event.timestamp;
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        if(count==0)
            time2 = System.nanoTime();

//        time2 = event.timestamp;      //time after getting values
//            //Toast.makeText(this, "x:"+x+"\n"+"y:"+y+"\n"+"z:"+z, Toast.LENGTH_SHORT).show();
//            //R.id.textView(Long.toString(time1));
//            //the force of gravity must be eliminated => problem of the direction of the phone

        textView.setText(Long.toString(time1));
////        setContentView(textViewTime2);
        if(count==0)
            time3 = System.nanoTime();
//        time3 = event.timestamp;
        textView2.setText(Long.toString(time2));
        textView3.setText(Long.toString(time3));
        textView4.setText("Accelerometer" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");

    }


    private void getMagnetic(SensorEvent event) {
        count = (count+1)%300;
        TextView textView =  (TextView) findViewById(R.id.textView);
        TextView textView2 =  (TextView) findViewById(R.id.textView2);
        TextView textView3 =  (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        if(count==0)
            time1 = System.nanoTime();     //time before getting values

        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];

        if(count==0)
            time2 = System.nanoTime();

        textView.setText(Long.toString(time1));
        if(count==0)
            time3 = System.nanoTime();

        textView2.setText(Long.toString(time2));
        textView3.setText(Long.toString(time3));
        textView4.setText("Ambiant magnetic field : " + x + " µT");


    }

    protected  void getSensorType(SensorEvent event){
        TextView textView =  (TextView) findViewById(R.id.textView);
//        TextView textView2 =  (TextView) findViewById(R.id.textView2);
//        TextView textView3 =  (TextView) findViewById(R.id.textView3);
//        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView.setText(Integer.toString(sensorType));
    }


    /////////////////////// End test
}
