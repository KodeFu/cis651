package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MasterDetail extends AppCompatActivity implements ListFragment.OnItemSelectedListener {
    private boolean twoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);
        if (savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.main_container, new ListFragment()).commit();
        }
        twoPane = false;
        if (findViewById(R.id.detail_container)!=null)
        {
            twoPane = true;
        }
    }

    @Override
    public void onListItemSelected(View sharedView, int imageResourceID, String title, String year) {

    }
}
