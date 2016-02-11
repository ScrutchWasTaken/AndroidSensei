package fr.scrutch.estelle.vmsalpha;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.scrutch.estelle.vmsalpha.db.SensorsDAO;
import fr.scrutch.estelle.vmsalpha.model.CustomAdapter;

public class MainActivity extends ListActivity {

    private SensorManager mSensorManager;
    private ArrayList<Integer> index = new ArrayList<>();

    private boolean[] checkedStates;

    private SensorsDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the sensor manager to get the sensors'list
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        /** DB STUFFS **/
        dao = new SensorsDAO(this);
        dao.open();

        for (int i = 0; i < deviceSensors.size(); i++) {
            try {
                dao.createSensor(deviceSensors.get(i).getName());
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        ArrayList<fr.scrutch.estelle.vmsalpha.model.Sensor> tmp = dao.getAllSensors();
        for (int i = 0; i < tmp.size(); i++) {
            System.out.println(tmp.get(i).getName());
        }
        /** END OF DB STUFFS **/

        final ListView listview = getListView();
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        listview.setTextFilterEnabled(true);
        Button goButton = new Button(this);
        Button histoButton = new Button(this);
        goButton.setText(R.string.Go);
        histoButton.setText(R.string.Historic);
        listview.addFooterView(goButton);
        listview.addFooterView(histoButton);
        setListAdapter(new CustomAdapter(this, deviceSensors));
        checkedStates = new boolean[listview.getCount()];

        //create the click listener:
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listview.getCount(); i++) {
                    //for all element of the list, i get the checked ones (boolean true for the checkedStates)
                    if (checkedStates[i] == true) {
                        index.add(i);
                    }
                }

                Intent intent = new Intent(MainActivity.this, MultipleSensorClickedActivity.class);
                //putExtra the index of sensors to send the int[] to MultipleSensorClickedActivity.java
                intent.putIntegerArrayListExtra("index", index);
                startActivity(intent);
                finish();
            }
        });

        histoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onListItemClick(ListView parent, View v, int position, long id) {
        checkedStates[position] = !checkedStates[position];
    }
}


