package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;

public class MasterDetailActivity extends AppCompatActivity {
    private boolean twoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail);
        if (savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                    new ListActivity()).commit();
        }
        twoPane = false;
        if (findViewById(R.id.detail_container)!=null)
        {
            twoPane = true;
        }
    }
}
