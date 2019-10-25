package com.example.lab3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment3 extends Fragment {
    private FragmentTracker ft;
    private View v;
    public static final String fragmentTitle="Details Info";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ft.fragmentVisible(fragmentTitle);
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.third_fragment, container, false);
        Button b_next = v.findViewById(R.id.next_button);
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.finished();
            }
        });
        Button b_back = v.findViewById(R.id.back_button);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.goBack();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            ft =  (FragmentTracker) context;
        }
        catch (ClassCastException ex) {
            throw new ClassCastException (context.toString() + "must implement EventTrack");
        }
    }

    @Override
    public void onDetach() {
        Log.d("xxx1", "onDetach called");
        super.onDetach();
        EditText language = v.findViewById(R.id.language);
        ft.saveLanguage(language.getText().toString());
        Log.d("xxx2", "onDetach called");
        v = null;
    }

}
