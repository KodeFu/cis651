package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
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

    public class DetailsTransition extends TransitionSet {
        public DetailsTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).addTransition(new ChangeTransform()).addTransition(new ChangeImageTransform());
        }
    }

    @Override
    public void onListItemSelected(View sharedView, int imageResourceID, String title, String year, String desc, float rating) {
        Bundle args = new Bundle();
        args.putInt("id", imageResourceID);
        args.putString("title", title);
        args.putString("year", year);
        args.putString("description", desc);
        args.putFloat("rating", rating);
        //Fragment detailFragement = new DetailFragment();
        Fragment detailFragement = new MovieDetailFragment();
        detailFragement.setArguments(args);
        if (twoPane)
        {
            detailFragement.setEnterTransition(new Slide(Gravity.TOP));
            detailFragement.setExitTransition(new Slide(Gravity.BOTTOM));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, detailFragement)
                    .addToBackStack(null).commit();
        }
        else
        {
            detailFragement.setSharedElementEnterTransition(new DetailsTransition());
            detailFragement.setEnterTransition(new Fade());
            detailFragement.setExitTransition(new Fade());
            detailFragement.setSharedElementReturnTransition(new DetailsTransition());
            getSupportFragmentManager().beginTransaction()
                    .addSharedElement(sharedView, ViewCompat.getTransitionName(sharedView))
                    .replace(R.id.main_container, detailFragement)
                    .addToBackStack(null).commit();
        }
    }
}
