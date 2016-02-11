package fr.scrutch.estelle.vmsalpha;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by scrutch on 11/02/16.
 */
public class FavoriteActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
    }
}
