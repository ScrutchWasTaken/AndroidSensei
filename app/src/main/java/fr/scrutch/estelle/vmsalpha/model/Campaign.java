package fr.scrutch.estelle.vmsalpha.model;

import java.util.List;

/**
 * Created by gael on 04/02/16.
 * Represent a measuring campaign to be saved in the DB
 */
public class Campaign {
    private long id;
    private String name;
    private long startDate;
    private long endDate;
    private String sensorName;
    private boolean isFav;

    public Campaign(long id, String name, long startDate, long endDate, String sensorName, boolean isFav) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sensorName = sensorName;
        this.isFav = isFav;
    }



    public String getName() {
        return name;
    }
}
