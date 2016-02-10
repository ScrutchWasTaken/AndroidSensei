package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scrutch on 10/02/16.
 */
public class MultipleSensorClickedActivity extends AppCompatActivity {

    private int count = 0;
    private SensorManager aSensorManager;   //one sensormanager for all listeners
    //    private SensorManager bSensorManager;
    private Sensor aSensor;
    private long time1,time2,time3;
    //    private int[] index;
    private SensorManager mSensorManager;   //don't touch that one
    private List<Sensor> sensorClicked;
    private ArrayList<Integer> index = new ArrayList<Integer>();

    private SensorEventThread sensorThread; //TEST #################
    private ArrayList<Sensor> sensorsToListen = new ArrayList<Sensor>();

    public MultipleSensorClickedActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.acceleration);
        //mSensorManager is just for the list of sensors, no other use
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);  //get the list of sensors
//        bSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorClicked = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        Intent intent = getIntent();
        //Get the int[] from the putExtra in the MainActivity.java
        index = intent.getIntegerArrayListExtra("index");
        System.out.println("index :"+index);
        //We need the sensor manager to get the list of sensors in which the index is related to
        aSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

//        textview.setText(sensorClicked.get(sensorType[0]).toString());    //Test: return the right sensor ;)
        // magnetic then accelero if clicked accelero then magnetic
//        for(int i = 0; i < index.size(); i++) {
//            System.out.println(sensorClicked.get(index.get(i)).toString());
//        }
        System.out.println("##############"+sensorClicked.get(index.get(0)).toString()+"erfed"+sensorClicked.get(index.get(1)).toString());
        //Create the list with the selected sensors:
        for(int i = 0; i < index.size(); i++) {
            sensorsToListen.add(sensorClicked.get(index.get(i)));
            System.out.println(sensorsToListen.get(i));
        }


        sensorThread = new SensorEventThread("SensorThread"); //TEST ###################
        setListeners(sensorsToListen);
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
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors LISTENER
        //TEST ################ decomment next line
//        aSensorManager.registerListener(this,aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
        //en avoir plein => correspond au nbr de sensors selected

//        aSensorManager.registerListener(sensorThread,aSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), 0,sensorThread.getHandler());

    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        //TEST ############## next line
//        aSensorManager.unregisterListener(this);

        sensorThread.quitLooper();
//        bSensorManager.unregisterListener(this);

    }


    ////////////////////////Other sensors:
    private void getAccelerometer(SensorEvent event) {
        count = (count+1)%300;  //to print just 1 value/300
        //We have to get the textView of the layout
//        TextView textView =  (TextView) findViewById(R.id.textView);
//        TextView textView2 =  (TextView) findViewById(R.id.textView2);
//        TextView textView3 =  (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        if(count==0)
            time1 = System.nanoTime();     //time before getting values
//            time1 = sensorClicked.get(sensorType[0]).getMaxDelay(); //minDelay = 10000 ms

        float[] values = event.values;
        // Movement, get values
        float x = values[0];
        float y = values[1];
        float z = values[2];

        if(count==0)
            time2 = System.nanoTime();


//        time2 = event.timestamp;      //time after getting values
//            //Toast.makeText(this, "x:"+x+"\n"+"y:"+y+"\n"+"z:"+z, Toast.LENGTH_SHORT).show();
//            //R.id.textView(Long.toString(time1));
//            //the force of gravity must be eliminated => problem of the direction of the phone

//        textView.setText("t1 = "+Long.toString(time1)+" ns");
////        setContentView(textViewTime2);
//        if(count==0)
//            time3 = System.nanoTime();
//        time3 = event.timestamp;
        //time to print, printed on screen:
//        textView2.setText("t2 = "+Long.toString(time2)+" ns");
//        textView3.setText("t3 = "+Long.toString(time3)+" ns");
        textView4.setText("Accelerometer" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");

    }


    private void getMagnetic(SensorEvent event) {
        count = (count+1)%300;
//        TextView textView =  (TextView) findViewById(R.id.textView);
//        TextView textView2 =  (TextView) findViewById(R.id.textView2);
        TextView textView3 =  (TextView) findViewById(R.id.textView3);
//        TextView textView4 = (TextView) findViewById(R.id.textView4);

        if(count==0)
            time1 = System.nanoTime();     //time before getting values

        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];
        float y = values[1];
        float z = values[2];
        //missing y and z

//        if(count==0)
//            time2 = System.nanoTime();

//        textView.setText(Long.toString(time1));
//        if(count==0)
//            time3 = System.nanoTime();
//
//        textView2.setText(Long.toString(time2));
//        textView3.setText(Long.toString(time3));
        textView3.setText("Ambiant magnetic field : " + x + " µT" +"\n"+ y + " µT" +"\n"+ z + " µT");


    }

    protected  void getSensorType(SensorEvent event){
        TextView textView =  (TextView) findViewById(R.id.textView);
        TextView textView2 =  (TextView) findViewById(R.id.textView2);
        TextView textView3 =  (TextView) findViewById(R.id.textView3);
//        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView.setText(sensorClicked.get(index.get(0)).toString());
        textView2.setText(sensorClicked.get(index.get(1)).toString());
//        textView3.setText(sensorClicked.get(index.get(2)).toString());
    }

    public void setListeners(ArrayList<Sensor> sensors){
        for (Sensor s:sensors) {
            aSensorManager.registerListener(sensorThread,aSensorManager.getDefaultSensor(s.getType()), 0,sensorThread.getHandler());
            System.out.println("COUCOU");
        }
    }

    /////////////////////// End test
}
