package fr.scrutch.estelle.vmsalpha;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;

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
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            getLinearAcceleration(event);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            getGyroscope(event);
        } else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            getGravity(event);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            getMagnetic(event);
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            getAccelerometer(event);
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            getLight(event);
        } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE){
            getPressure(event);
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            getProximity(event);
        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            getRelativeHumidity(event);
        } else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            getTemperature(event);
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

    private void getGravity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Gravity" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");
    }

    private void getGyroscope(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Gyroscope" + "\n" + "x:" + x + " rad/s" + "\n" + "y:" + y + " rad/s" + "\n" + "z:" + z + " rad/s");
    }

    private void getMagnetic(SensorEvent event) {
        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Ambiant magnetic field : " + x + " µT" +"\n"+ y + " µT" +"\n"+ z + " µT");
    }

    private void getLinearAcceleration(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        System.out.println("Linear Acceleration" + "\n" + "x:" + x + " m.s²" + "\n" + "y:" + y + " m.s²" + "\n" + "z:" + z + " m.s²");
    }

    private void getLight(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        System.out.println("Light" + "\n" + "x:" + x + " lx");
    }

    private void getPressure(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        System.out.println("Pressure" + "\n" + "x:" + x + " hPa");
    }

    private void getProximity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        System.out.println("Proximity" + "\n" + "x:" + x + " cm");
    }

    private void getRelativeHumidity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        System.out.println("Relative Humidity" + "\n" + "x:" + x + " %");
    }

    private void getTemperature(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        System.out.println("Temperature" + "\n" + "x:" + x + " °C = " + (x+273.15) + " K");
    }

}
