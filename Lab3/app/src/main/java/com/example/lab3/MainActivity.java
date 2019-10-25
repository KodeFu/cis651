package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentTracker {
    private Fragment2 fragment2;
    private Fragment1 fragment1;
    private Fragment3 fragment3;
    private GestureDetectorCompat mDetector;
    private final PersonInfo pi = new PersonInfo();
    private int next = 2;
    private int back = 1;

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (event1.getX() < event2.getX()){
                //Toast toast = Toast.makeText(MainActivity.this, "Fling right", Toast.LENGTH_SHORT);
                //toast.show();
                goBack();
            }
            else
            {
                //Toast toast = Toast.makeText(MainActivity.this, "Fling left", Toast.LENGTH_SHORT);
                //toast.show();
                goNext();
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        mDetector = new GestureDetectorCompat(this,new MyGestureListener());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadTheFragment(fragment1);
        next = 2;
        back = 1;
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
        switch (next) {
            case 1:
                loadTheFragment(fragment1);
                next = 2;
                back = 3;
                break;
            case 2:
                loadTheFragment(fragment2);
                next = 3;
                back = 1;
                break;
            case 3:
                loadTheFragment(fragment3);
                next = 1;
                back = 2;
            default:
                break;
        }

    }

    @Override
    public void goBack() {
        switch (back) {
            case 1:
                loadTheFragment(fragment1);
                next = 2;
                back = 3;
                break;
            case 2:
                loadTheFragment(fragment2);
                next = 3;
                back = 1;
                break;
            case 3:
                loadTheFragment(fragment3);
                next = 1;
                back = 2;;
            default:
                break;
        }

    }

    @Override
    public void saveNameAndLastName(String name, String lname) {
        pi.setName(name);
        pi.setLastname(lname);
    }

    @Override
    public void saveCityAndZip(String city, String zip) {
        pi.setCity(city);
        pi.setZip(zip);
    }

    @Override
    public void saveLanguage(String language) {
        pi.setLanguage(language);
    }

    @Override
    public void finished() {
        Log.d("xxx", "finished()");
        Intent i = new Intent(this, SummaryActivity.class );
        i.putExtra("pi", pi);
        startActivity(i);
    }
}
