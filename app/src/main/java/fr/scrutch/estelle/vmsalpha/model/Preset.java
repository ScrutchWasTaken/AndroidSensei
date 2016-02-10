package fr.scrutch.estelle.vmsalpha.model;

import android.hardware.Sensor;

/**
 * Created by gael on 04/02/16.
 * Will stock the info about a sensor used in a preset
 * or during a campaign
 */
public class Preset {

    private Sensor sensor;
    private long speed;

    public Preset(Sensor sensor, long speed) {
        this.sensor = sensor;
        this.speed = speed;
    }
}
