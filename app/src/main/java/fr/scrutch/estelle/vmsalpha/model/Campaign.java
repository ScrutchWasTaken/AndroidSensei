package fr.scrutch.estelle.vmsalpha.model;

import java.util.List;

/**
 * Created by gael on 04/02/16.
 * Represent a measuring campaign to be saved in the DB
 */
public class Campaign {
    private String name;
    private long startDate;
    private long endDate;
    private Sensor sensor;
    private boolean isFav;

    public Campaign(String name ,Sensor sensor, long startDate, boolean isFav) {
        this.name = name;
        this.startDate = startDate;
        this.sensor = sensor;
        this.isFav = isFav;
    }
}
