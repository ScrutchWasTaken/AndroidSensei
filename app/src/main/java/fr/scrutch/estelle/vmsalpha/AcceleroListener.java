package fr.scrutch.estelle.vmsalpha;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by scrutch on 05/02/16.
 */
public class AcceleroListener implements SensorEventListener {

    private int count;
    private long time1, time2;

    @Override
    public void onSensorChanged(SensorEvent event) {
        getAccelerometer(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void getAccelerometer(SensorEvent event) {
        count = (count+1)%300;  //to print just 1 value/300
        //We have to get the textView of the layout
//        TextView textView =  (TextView) findViewById(R.id.textView);
//        TextView textView2 =  (TextView) findViewById(R.id.textView2);
//        TextView textView3 =  (TextView) findViewById(R.id.textView3);
//        TextView textView4 = (TextView) findViewById(R.id.textView4);

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
//        textView4.setText("Accelerometer" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");

    }
}
