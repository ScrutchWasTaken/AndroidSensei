package fr.scrutch.estelle.vmsalpha;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.scrutch.estelle.vmsalpha.db.CampaignsDAO;

/**
 * Created by scrutch on 10/02/16.
 */
public class MultipleSensorClickedActivity extends AppCompatActivity {

    private SensorManager aSensorManager;   //one sensormanager for all listeners
    private SensorManager mSensorManager;   //don't touch that one
    private List<Sensor> sensorClicked;
    private ArrayList<Integer> index = new ArrayList<Integer>();

    private SensorEventThread sensorThread; //TEST #################
    private ArrayList<Sensor> sensorsToListen = new ArrayList<Sensor>();
    private boolean first = true;
    private EditText campaign;
    private String campaignName;

    public MultipleSensorClickedActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.multiple_sensors);
        //mSensorManager is just for the list of sensors, no other use
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);  //get the list of sensors
        sensorClicked = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        Intent intent = getIntent();
        //Get the index as arraylist<Integer> from the putExtra in the MainActivity.java
        index = intent.getIntegerArrayListExtra("index");
        //We need the sensor manager to get the list of sensors in which the index is related to
        aSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Create the list with the selected sensors:
        for(int i = 0; i < index.size(); i++) {
            sensorsToListen.add(sensorClicked.get(index.get(i)));
            TextView tv = (TextView) findViewById(R.id.sensorsList);
            tv.setText(tv.getText()+sensorClicked.get(index.get(i)).getName()+"\n");
        }

        campaign = (EditText) findViewById(R.id.editTextView);

        if(campaignName!=null) {
            sensorThread = new SensorEventThread("SensorThread", campaignName, this);
        } else {
            Toast.makeText(this, "Set first please", Toast.LENGTH_LONG);
        }

//        setListeners(sensorsToListen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensors_list, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        if(first==true) {
            first = false;
        } else {
            setListeners(sensorsToListen);
        }
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        unsetListeners(sensorsToListen);
        sensorThread.quitLooper();
    }


    public void setListeners(ArrayList<Sensor> sensors){
        for (Sensor s:sensors) {
            aSensorManager.registerListener(sensorThread, aSensorManager.getDefaultSensor(s.getType()), 0,sensorThread.getHandler());
        }
    }

    public void unsetListeners(ArrayList<Sensor> sensors){
        aSensorManager.unregisterListener(sensorThread);
    }

    public void onClickStart(View v){
        onResume();
    }

    public void onClickStop(View v){
        onPause();
    }

    public void onClickList(View v){
        Intent intent = new Intent(MultipleSensorClickedActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickFavorite(View v){
        /*campaignName = campaign.getText().toString();
        //le nom à mettre est campaignName ;)
        System.out.println(campaignName);

        /** SAVING THE CAMPAIGN IN THE DB **/
        /*CampaignsDAO dao = new CampaignsDAO(this);
        dao.open();

        //@TODO Handle the case when the Canpaign allready exist
        for(int i=0 ; i<sensorsToListen.size() ; i++) {
            dao.createCampaign(campaignName, System.currentTimeMillis(), sensorsToListen.get(i).getName(), true);
        }
        Toast.makeText(this, "Saved in the DB", Toast.LENGTH_LONG).show();

        dao.close();*/
        campaignName = campaign.getText().toString();




    }

}
