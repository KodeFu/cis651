package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set main activity as current view
        setContentView(R.layout.activity_main);

        // setup toolbar
        toolbar = (Toolbar) findViewById(R.id.drawer_toolbar);
        setSupportActionBar(toolbar);

        // glue navigation to the toolbar
        navigationView = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.ndopen, R.string.ndclose) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("onDrawer", "closed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.d("onDrawer", "opened");
            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_action:
                Log.d("onOptionsItemSelected", "about");
                //setContentView(R.layout.viewpager_layout);
                //Intent i = new Intent(this, PagerActivity.class );
                //i.putExtra("pi", pi);
                //startActivity(i);
                return true;
            case R.id.task1_action:
                Log.d("onOptionsItemSelected", "task1");
                //setContentView(R.layout.viewpager_layout);
                Intent i = new Intent(this, PagerActivity.class );
                //i.putExtra("pi", pi);
                startActivity(i);
                return true;
            case R.id.task2_action:
                Log.d("onOptionsItemSelected", "task2");
                Intent l = new Intent(this, ListActivity.class );
                //i.putExtra("pi", pi);
                startActivity(l);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_about_action:
                Log.d("onNav", "about");
                return true;
            case R.id.drawer_task1_action:
                Log.d("onNav", "task1");
                Intent i = new Intent(this, PagerActivity.class );
                //i.putExtra("pi", pi);
                startActivity(i);
                return true;
            case R.id.drawer_task2_action:
                Log.d("onNav", "task2");
                Intent l = new Intent(this, ListActivity.class );
                //i.putExtra("pi", pi);
                startActivity(l);
                return true;
            default:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
    }

}
