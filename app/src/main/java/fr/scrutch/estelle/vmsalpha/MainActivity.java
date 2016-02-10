package fr.scrutch.estelle.vmsalpha;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//Ici, c'est Scrutchy
public class MainActivity extends ListActivity {

    private SensorManager mSensorManager;
    private List<Sensor> sensorClicked;
    private ArrayList<Integer> index = new ArrayList<Integer>();

//    private List<Sensor> sensorSelected;
//    private String theSensorClicked;
//    public final static String EXTRA_MESSAGE = "fr.scrutch.estelle.vmsalpha.MESSAGE";
    private boolean[] checkedStates;
    private String sensorSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the sensor manager to get the sensors'list
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Create the list (cf http://www.androidinterview.com/android-custom-listview-with-checkbox-example/)
        final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorClicked = deviceSensors;
//        final ArrayList<Object> deviceSensorsO = new ArrayList<Object>();
//        for (Sensor s:deviceSensors) {
//            deviceSensorsO.add((Object) s);
//        }
//        final List<Object> deviceSensorsO = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        final ListView listview = getListView();
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        listview.setTextFilterEnabled(true);
        setListAdapter(new CustomAdapter(this, deviceSensors));
        checkedStates = new boolean[listview.getCount()];

        ////////////////////////////////////////////////////////////////////////////////////////
        //Find the button in the view
        Button goButton = (Button) findViewById(R.id.goButton);

        //create the click listener:
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                index=new int[listview.getCount()];
//                int p = 0;

                for(int i =0; i < listview.getCount();i++){
                    System.out.println("getcount :"+listview.getCount());
                    System.out.println("getcount then i :"+i);
                    //for all element of the list, i get the checked ones (boolean true for the checkedStates)
                    if(checkedStates[i]==true){
                        index.add(i);
                    }
                }

                Intent intent = new Intent(MainActivity.this, MultipleSensorClickedActivity.class);
                //putExtra the index of sensors to send the int[] to MultipleSensorClickedActivity.java
                intent.putIntegerArrayListExtra("index",index);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


///////////////////////////////////////////////////////////////////////

	public void onListItemClick(ListView parent, View v,int position,long id){
        //CheckedTextView can be created in the view, not necessary now
//		CheckedTextView item = (CheckedTextView) v;
        checkedStates[position] = !checkedStates[position]; //checked are unchecked...
//        Toast.makeText(this, sensorClicked.get(position)+ " checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();


	}
}


