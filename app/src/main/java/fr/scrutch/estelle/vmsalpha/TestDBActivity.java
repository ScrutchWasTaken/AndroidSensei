package fr.scrutch.estelle.vmsalpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;
import java.util.List;

public class TestDBActivity extends AppCompatActivity {

    private MeasuresDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        dataSource = new MeasuresDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Measure> measures = dataSource.getAllMeasures();

    }

    public void onClick(View view) {
        Measure m = new Measure();
        for(int i=0 ; i<100 ; i++) {
            //m.setTime(System.currentTimeMillis());
            m = dataSource.createMeasure(System.currentTimeMillis());
            //System.out.println(measure);
        }

        System.out.println("Finished to write in the DB");
    }


    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }


}
