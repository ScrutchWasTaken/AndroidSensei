package fr.scrutch.estelle.vmsalpha;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by scrutch on 11/02/16.
 */
public class HistoricActivity extends ListActivity {

    private ArrayList<String> campaignName = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic);


        final ListView listview = getListView();
        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,campaignName);
        setListAdapter(list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //@TODO activity to print measures
            }
        });

    }


    //Gael, je te laisse aller chercher la liste des noms de campagnes
    //@TODO get campaign name in the database
    public ArrayList<String> getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(ArrayList<String> campaignName) {
        this.campaignName = campaignName;
    }
}
