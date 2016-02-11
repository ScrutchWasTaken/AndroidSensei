package fr.scrutch.estelle.vmsalpha.model;

/**
 * Created by gael on 04/02/16.
 *
 * Package a measure from a sensors at a given time
 * Will be save into a table which with the sensors name
 * and the campaign from which the measure come from.
 *
 */
public class Measure {
    private int id;
    private String sensorName;
    private String campaignName;
    private long time;
    private float value1, value2, value3;

    public Measure(int id, String sensorName, String campaignName, long time, float value1, float value2, float value3) {
        this.id = id;
        this.sensorName = sensorName;
        this.campaignName = campaignName;
        this.time = time;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Measure(String sensorName, String campaignName, long time, float value1, float value2, float value3) {
        this.sensorName = sensorName;
        this.campaignName = campaignName;
        this.time = time;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Measure(String sensorName, String campaignName, long time, float value1) {
        this.sensorName = sensorName;
        this.campaignName = campaignName;
        this.time = time;
        this.value1 = value1;
    }

    public long getTime() {
        return time;
    }

    public float getValue1() {
        return value1;
    }

    public float getValue2() {
        return value2;
    }

    public float getValue3() {
        return value3;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getCampaignName() {
        return campaignName;
    }
}
