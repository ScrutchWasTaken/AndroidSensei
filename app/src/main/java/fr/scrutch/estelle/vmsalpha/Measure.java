package fr.scrutch.estelle.vmsalpha;

/**
 * Created by gael on 13/11/15.
 */
public class Measure {
    private long id;
    private long time;

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return Long.toString(time);
    }
}
