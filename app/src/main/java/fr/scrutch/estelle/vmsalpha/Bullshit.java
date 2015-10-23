package fr.scrutch.estelle.vmsalpha;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gael on 23/10/15.
 */
public class Bullshit {
    private long id;
    private Date time;


    public long getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time.toString();
    }
}
