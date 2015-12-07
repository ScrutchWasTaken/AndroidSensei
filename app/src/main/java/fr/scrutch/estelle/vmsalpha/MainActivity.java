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

import java.util.ArrayList;
import java.util.List;

//Ici, c'est Scrutchy
public class MainActivity extends ListActivity {

    private SensorManager mSensorManager;
    private List<Sensor> sensorClicked;
//    private String theSensorClicked;
//    private int[] index;
//    public final static String EXTRA_MESSAGE = "fr.scrutch.estelle.vmsalpha.MESSAGE";
    private List<CheckBox> checked;

//    final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

//    String[] city= {
//            "Bangalore",
//            "Chennai",
//            "Mumbai",
//            "Pune",
//            "Delhi",
//            "Jabalpur",
//            "Indore",
//            "Ranchi",
//            "Hyderabad",
//            "Ahmedabad",
//            "Kolkata",
//            "Bhopal"
//    };    //TEST

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
        ListView listview = getListView();
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        listview.setTextFilterEnabled(true);
        setListAdapter(new CustomAdapter(this, deviceSensors));
//        setListAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_checked,deviceSensorsO));


//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //Il trouve la position et l'id => COMMENT? => faire pareil
        //Qui clique? Il le savait! => Passer par le parent.
        //tag ou id.
        //breakpoint et lancer en debug breakpoint au niveau du onClick => voir les méthodes appelées long id int position
        //ou liste des vues enfant, en cherchant dans la liste, on a la position. Liste non publique
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  //replaced by an "onclick" for checkboxes
//                //This function changes the activity (selecting the sensor)
//
//                //Create the object showed
//                //sensorClicked = ((TextView)view).getText().toString();
////                sensorClicked = (Sensor)parent.getItemIdAtPosition(position);   //getItemAtPosition(position);
//                sensorClicked = (Sensor)parent.getItemAtPosition(position);
////                System.out.println("//////////////////////////////////////////////// "+sensorClicked);
//                //theSensorClicked = sensorClicked.toString();
//                //Toast is to show a variable in a little window that appears and disapperas quite quickly
//                //Toast.makeText(getBaseContext(), sensorClicked, Toast.LENGTH_LONG).show(); //parameters: context, text, time shown
//                //Next is the function to change the view (with the name of the sensor (1st) or the object (2nd)
//                goSensorClicked();
//                //goClicked();
//            }
//        });
        ////////////////////////////////////////////////////////////////////////////////////////

//        			setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,city));  //TEST

        ////////////////////////////////////////////////////////////////////////////////////////
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SensorClickedActivity.class);
//                selection = adapter.getSensors();

//                intent.putExtra("sensorType",index);
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
//        intent.putExtra("sensorType", sensorClicked.getType());
//        startActivity(intent);
//    }

//    public void onCheckboxClicked(View view) {
//    // Is the view now checked?
//    boolean checked = ((CheckBox) view).isChecked();
//
//    // Check which checkbox was clicked
//    switch(view.getId()) {
//        case R.id.che:
//            if (checked)
//                // Put some meat on the sandwich
//            else
//                // Remove the meat
//            break;
//        case R.id.checkbox_cheese:
//            if (checked)
//                // Cheese me
//            else
//                // I'm lactose intolerant
//            break;
//    }
///////////////////////////////////////////////////////////////////////

	public void onListItemClick(ListView parent, View v,int position,long id){
		CheckedTextView item = (CheckedTextView) v;
		Toast.makeText(this, sensorClicked.get(position).getName()+ " checked : " +
		item.isChecked(), Toast.LENGTH_SHORT).show();
	}

}


