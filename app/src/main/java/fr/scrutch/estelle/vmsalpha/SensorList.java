package fr.scrutch.estelle.vmsalpha;

import android.hardware.Sensor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by scrutch on 10/12/15.
 */
public class SensorList implements Serializable {
    private List<Sensor> sensors;

    public SensorList(List<Sensor> sensorsList){
        sensors = sensorsList;
    }

    public void setList(List<Sensor> l){
        sensors = l;
    }
}
