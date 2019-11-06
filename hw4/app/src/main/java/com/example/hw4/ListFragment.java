package com.example.hw4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ListFragment extends Fragment {
    public interface OnItemSelectedListener {
        public void onListItemSelected(View sharedView, int imageResourceID, String title, String year, String desc, float rating);
    }
    OnItemSelectedListener clickListener;
    private MovieData md=new MovieData();
    private final MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(md.getMoviesList());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView rv = rootView.findViewById(R.id.mainRecyclerView);
        //StaggeredGridLayoutManager layoutManager =
        //        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        //      layoutManager.setOrientation(LinearLayout.VERTICAL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myRecyclerAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            clickListener = (OnItemSelectedListener) context;
            myRecyclerAdapter.setOnItemClickListener(clickListener);
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException(context.toString() + "must implement EventTrack");
        }
    }
}
