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
    private long id;
    private String sensorName;
    private String campaignName;
    private long time;
    private long value1, value2, value3;

    public Measure(long id, String sensorName, String campaignName, long time, long value1, long value2, long value3) {
        this.id = id;
        this.sensorName = sensorName;
        this.campaignName = campaignName;
        this.time = time;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Measure(long id, String sensorName, String campaignName, long time, long value1) {
        this.id = id;
        this.sensorName = sensorName;
        this.campaignName = campaignName;
        this.time = time;
        this.value1 = value1;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getCampaignName() {
        return campaignName;
    }
}
