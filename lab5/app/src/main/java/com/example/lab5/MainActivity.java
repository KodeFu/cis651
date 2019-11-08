package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void NextActivity(View view) {
        Intent intent = new Intent(this, MasterDetail.class);
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "newActivity");
        startActivity(intent, optionsCompat.toBundle());
    }

    public void MoveAndRotate(View view) {
        ImageView imageView = findViewById(R.id.image_view);
        imageView.animate().setDuration(1000).x(500).y(800).rotationYBy(720).scaleX(.4F).scaleY(.4F);
    }

    public void MoveBack(View view) {
        ImageView imageView = findViewById(R.id.image_view);
        imageView.animate().setDuration(1000).x(imageView.getLeft()).y(imageView.getTop()).rotationYBy(720).scaleX(1F).scaleY(1F);
    }

    public void FadeOut(View view) {
        ImageView imageView = findViewById(R.id.image_view);
        imageView.animate().setDuration(1000).alpha(0F);
    }

    public void FadeIn(View view) {
        ImageView imageView = findViewById(R.id.image_view);
        imageView.animate().setDuration(1000).alpha(1F);
    }

    public void XMLAnimator(View view) {
        ImageView imageView = findViewById(R.id.image_view);
        AnimatorSet spinSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator);
        spinSet.setTarget(imageView);
        spinSet.start();
    }
}
