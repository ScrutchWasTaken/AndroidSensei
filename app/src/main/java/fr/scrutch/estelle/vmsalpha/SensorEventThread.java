package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import java.util.ArrayList;

import fr.scrutch.estelle.vmsalpha.db.MeasuresDAO;
import fr.scrutch.estelle.vmsalpha.model.Measure;

/**
 * Created by scrutch on 10/02/16.
 */
public class SensorEventThread extends HandlerThread implements SensorEventListener {

    private Handler handler;
    private ArrayList<Measure> measures;
    private String campaignName ;
    private MeasuresDAO dao;
    private Sensor currentSensor;
    private String sensorName;

    public SensorEventThread(String name, String campaignName, Context context) {
        super(name);
        this.campaignName=campaignName;
        dao = new MeasuresDAO(context);
        measures = new ArrayList<>();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        currentSensor = event.sensor;
        sensorName = currentSensor.getName();

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
        /** Saving in the DB **/
        dao.open();
        System.out.println("Starting adding " + measures.size() + " measures in the DB..." );

        /* NON OPTIMISED METHODE */
        for(int i=0; i<measures.size();i++) {
            dao.createMeasure(measures.get(i));
        }

        /** OPTIMISED METHOD :-D **/
        /*dao.createMeasures(measures);
        measures.clear();
        System.out.println("Added " + measures.size() + " measures to the DB.");*/
        dao.close();
    }

    private void getAccelerometer(SensorEvent event) {

        float[] values = event.values;
        // Movement, get values
        float x = values[0];
        float y = values[1];
        float z = values[2];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x, y, z));
    }

    private void getGravity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x, y, z));
    }

    private void getGyroscope(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x, y, z));
    }

    private void getMagnetic(SensorEvent event) {
        float[] values = event.values;
        //micro-Tesla µT ou uT
        float x = values[0];
        float y = values[1];
        float z = values[2];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x, y, z));
    }

    private void getLinearAcceleration(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x, y, z));
    }

    private void getLight(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x));
    }

    private void getPressure(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x));
    }

    private void getProximity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x));
    }

    private void getRelativeHumidity(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x));
    }

    private void getTemperature(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        measures.add(new Measure(sensorName, campaignName, System.currentTimeMillis(), x));
    }

}
