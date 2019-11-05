package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MovieData md = new MovieData();
    private final MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(md.getMoviesList());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void onStart() {
        super.onStart();
        recycler_view = (RecyclerView) findViewById(R.id.mainRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);
        myRecyclerAdapter.setOnItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Map hashMap = myRecyclerAdapter.getItem(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance((int) hashMap.get("image"),
                        hashMap.get("name").toString(), hashMap.get("year").toString(),
                        Float.parseFloat(hashMap.get("rating").toString()), hashMap.get("description").toString());
                fragmentTransaction.replace(R.id.detailFragment, movieDetailFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                fragmentTransaction.commit();
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Map hashMap = myRecyclerAdapter.getItem(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance((int) hashMap.get("image"),
                        hashMap.get("name").toString(), hashMap.get("year").toString(),
                        Float.parseFloat(hashMap.get("rating").toString()), hashMap.get("description").toString());
                fragmentTransaction.replace(R.id.detailFragment, movieDetailFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                fragmentTransaction.commit();
            }
        });
        recycler_view.setAdapter(myRecyclerAdapter);
        recycler_view.setItemAnimator(new DefaultItemAnimator());

    }
}
