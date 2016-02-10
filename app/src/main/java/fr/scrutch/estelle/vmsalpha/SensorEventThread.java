package fr.scrutch.estelle.vmsalpha;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.TextView;

/**
 * Created by scrutch on 10/02/16.
 */
public class SensorEventThread extends HandlerThread implements SensorEventListener {

    Handler handler;

    public SensorEventThread(String name) {
        super(name);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        Log.v("SensorTest", "onSensorChanged");
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
//            getAccelerometer(event);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
        } else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            getMagnetic(event);
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            getAccelerometer(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        handler = new Handler(this.getLooper());
    }

    public Handler getHandler() {
        return handler;
    }

    public void quitLooper() {
        if (this.isAlive()) {
            this.getLooper().quit();
        }
    }

    private void getAccelerometer(SensorEvent event) {

        float[] values = event.values;
        // Movement, get values
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Accelerometer" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");
    }

    private void getMagnetic(SensorEvent event) {
        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Ambiant magnetic field : " + x + " µT" +"\n"+ y + " µT" +"\n"+ z + " µT");
    }


}
