package fr.scrutch.estelle.vmsalpha;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import fr.scrutch.estelle.vmsalpha.db.CampaignsDAO;
import fr.scrutch.estelle.vmsalpha.model.Campaign;

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
        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getCampaignName());
        setListAdapter(list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoricActivity.this, MeasuresActivity.class);
                intent.putExtra("campaignClicked",getCampaignName().get(position));
                startActivity(intent);
                finish();
            }
        });

    }

    public ArrayList<String> getCampaignName() {
        CampaignsDAO dao = new CampaignsDAO(this);
        dao.open();
        ArrayList<Campaign> campaigns = dao.getAllCampaigns();
        for(int i=0;i<campaigns.size();i++) {
            campaignName.add(campaigns.get(i).getName());
        }
        return campaignName;
    }

    public void setCampaignName(ArrayList<String> campaignName) {
        this.campaignName = campaignName;
    }
}
