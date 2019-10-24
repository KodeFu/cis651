package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FragmentTracker {
    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();
    private Fragment3 fragment3 = new Fragment3();
    //private GestureDetectorCompat mDetector;
    //private final PersonInfo pi = new PersonInfo();
    private int next = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadTheFragment(fragment1);
    }

    private void loadTheFragment(Fragment f)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
    }

    @Override
    public void fragmentVisible(String s) {
        TextView textView = findViewById(R.id.title);
        textView.setText(s);
    }

    @Override
    public void goNext() {
        loadTheFragment(fragment2);

    }

    @Override
    public void goBack() {

    }

    @Override
    public void saveNameAndLastName(String name, String lname) {

    }

    @Override
    public void saveCityAndZip(String city, String zip) {

    }

    @Override
    public void saveLanguage(String language) {

    }

    @Override
    public void finished() {

    }
}
