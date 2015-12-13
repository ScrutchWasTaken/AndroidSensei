package fr.scrutch.estelle.vmsalpha;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//Ici, c'est Scrutchy
public class MainActivity extends ListActivity {

    private SensorManager mSensorManager;
    private List<Sensor> sensorClicked;
    private List<Sensor> sensorsList;

    //    private List<Sensor> sensorSelected;
//    private String theSensorClicked;
//    private int[] index;
//    public final static String EXTRA_MESSAGE = "fr.scrutch.estelle.vmsalpha.MESSAGE";
    private boolean[] checkedStates;
    private String sensorSelected;
    private String sensorType;

//    final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

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
//        setListAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_checked,deviceSensorsO));
        checkedStates = new boolean[listview.getCount()];

        ////////////////////////////////////////////////////////////////////////////////////////
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getItemId ou getItem?? for next, test with getItem first
                for(int i =0; i < listview.getCount();i++){

                    if(checkedStates[i]==true){
                        System.out.println("################"+listview.getItemAtPosition(i));
                        if(sensorSelected==null) {
                            sensorSelected = listview.getItemAtPosition(i).toString();
                        }
                        else {
                            sensorSelected = sensorSelected + "/" + listview.getItemAtPosition(i).toString();
//                            sensorsList.add(deviceSensors.get(i));
                            System.out.println("##################deviceSensors"+deviceSensors.get(i));
//                            System.out.println(listview.getItemAtPosition(i));
                        }
                    }
                }
                SensorList sensorsPutExtra = new SensorList(sensorsList);
                //################### TEST
                // save the object to file
                FileOutputStream fos = null;
                ObjectOutputStream out = null;
                try {
                    fos = new FileOutputStream(sensorType);
                    out = new ObjectOutputStream(fos);
                    out.writeObject(sensorsPutExtra);

                    out.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //##################### END TEST
//                System.out.println("########################"+sensorsList.get(0));

                Intent intent = new Intent(MainActivity.this, SensorClickedActivity.class);
                intent.putExtra("sensorType",sensorsPutExtra);
//                intent.putExtra("sensors",sensorSelected);
//                Bundle test =new Bundle().getBundle
//                intent.putExtra("sensors",sensorForBundle);
                /*////*//*
                Here: sending the information about which sensor is selected
                *//*////*/
//                System.out.println("////////////////////////////////////////////////////"+index);
                startActivity(intent);
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

//    //Change the Activity but doesn't get the sensor yet
//    public void goSensorClicked() {
//        Intent intent = new Intent(this, SensorClickedActivity.class);
//        //could try to putExtra the sensor itself
////        intent.putExtra(EXTRA_MESSAGE, sensorClicked.getName());
    /////////////////////////////////////////////////////////////////// ##################
//        intent.putExtra("sensorType", sensorClicked.getType());
//        startActivity(intent);
//    }


///////////////////////////////////////////////////////////////////////

	public void onListItemClick(ListView parent, View v,int position,long id){

		CheckedTextView item = (CheckedTextView) v;
        checkedStates[position] = !checkedStates[position];
        Toast.makeText(this, sensorClicked.get(position)+ " checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();


//        sensorSelected.add(sensorClicked.get(position));

	}

}


