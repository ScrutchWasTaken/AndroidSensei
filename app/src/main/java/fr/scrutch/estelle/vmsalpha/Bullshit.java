package fr.scrutch.estelle.vmsalpha;

/**
 * Created by gael on 23/10/15.
 * Bullshit : the type of data stored in the DB
 * For benchmarking purpose
 */
public class Bullshit {
    public long id;
    public long time;


    public long getId() {
        return this.id;
    }

    public long getTime() {
        return this.time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return (Long.toString(getTime()));
    }
}
