package fr.scrutch.estelle.vmsalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.scrutch.estelle.vmsalpha.db.MeasuresDAO;
import fr.scrutch.estelle.vmsalpha.model.Measure;

/**
 * Created by scrutch on 11/02/16.
 */
public class MeasuresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measures);

        Intent intent = getIntent();
        String campaignName = intent.getStringExtra("campaignClicked");
        System.out.println(campaignName);

        //@TODO aller chercher les mesures correspondant au nom de la campagne
        MeasuresDAO dao = new MeasuresDAO(this);
        dao.open();
        ArrayList<Measure> measures = dao.getMeasuresForCampaign(campaignName);
        dao.close();

        System.out.println("Mesures : " + measures);
        System.out.println("0"+measures.get(0)+"1"+measures.get(1));

        ListView listview = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,measures);
        listview.setAdapter(adapter);
    }
}
