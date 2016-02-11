package fr.scrutch.estelle.vmsalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by scrutch on 11/02/16.
 */
public class MeasuresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measures);

        Intent intent = getIntent();
        intent.getStringExtra("campaignClicked");

        //@TODO aller chercher les mesures correspondant au nom de la campagne
    }
}
