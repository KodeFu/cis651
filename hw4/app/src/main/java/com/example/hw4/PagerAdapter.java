package com.example.hw4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private MovieData md;

    public PagerAdapter(@NonNull FragmentManager fm, MovieData list_m) {
        super(fm);
        this.md = list_m;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        HashMap movieDataItem = md.getItem(position);
        Fragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", (int) movieDataItem.get("image"));
        args.putString("title", (String) movieDataItem.get("name"));
        args.putString("year", (String) movieDataItem.get("year"));
        args.putDouble("rating", (double) movieDataItem.get("rating"));
        args.putString("description", (String) movieDataItem.get("description"));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return md.getSize();
    }
}
