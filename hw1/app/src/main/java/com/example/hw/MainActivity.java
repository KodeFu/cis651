package com.example.hw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTask0(View view) {
        setContentView(R.layout.activity_task0);
    }

    public void onTask1(View view) {
        setContentView(R.layout.activity_task1);
    }

    public void onTask2(View view) {
        setContentView(R.layout.activity_task2);
    }

    public void onTask3(View view) {
        // Have to create view before trying to get element and add listeners
        setContentView(R.layout.activity_task3);

        // Get seekbar and add listener for changed event
        SeekBar sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("SeekBar", "onProgressChanged called");

                // images are 214x317
                // minimum is 214x317
                // middle  is 472x699
                // maximum is 729x1080
                // each step max - min
                //   width 515 / 100 -> 5.15 each step
                //   height 763 / 100 -> 7.63 each step

                // get current progress value
                int currentProgress = seekBar.getProgress();

                // Get the imageView
                ImageView imageView = findViewById(R.id.imageView);
                imageView.requestLayout();
                int x = imageView.getLayoutParams().height;
                int y = imageView.getLayoutParams().width;
                Log.d("SeekBar", "progress " + currentProgress );
                Log.d("SeekBar", "height " + y );
                Log.d("SeekBar", "width " + x);
                imageView.getLayoutParams().height = 317 + (int) ((float)currentProgress * 7.63);
                imageView.getLayoutParams().width = 214 + (int) ((float)currentProgress * 5.15);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SeekBar", "onStartTrackingTouch called");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SeekBar", "onStopTrackingTouch called");
            }
        });
    }

    public void onTask4(View view) {
    }

    public void onTask0ButtonClicked(View view) {
        Integer id = view.getId();
        Button button = null;

        switch (id) {
            case R.id.task0button1:
                button = findViewById(R.id.task0button1);
                break;
            case R.id.task0button2:
                button = findViewById(R.id.task0button2);
                break;
            case R.id.task0button3:
                button = findViewById(R.id.task0button3);
                break;
            case R.id.task0button4:
                button = findViewById(R.id.task0button4);
                break;
            case R.id.task0button5:
                button = findViewById(R.id.task0button5);
                break;
            case R.id.task0button6:
                button = findViewById(R.id.task0button6);
                break;
            case R.id.task0button7:
                button = findViewById(R.id.task0button7);
                break;
            case R.id.task0button8:
                button = findViewById(R.id.task0button8);
                break;
            case R.id.task1Button1:
                button = findViewById(R.id.task1Button1);
                break;
            case R.id.task1Button2:
                button = findViewById(R.id.task1Button2);
                break;
            case R.id.task1Button3:
                button = findViewById(R.id.task1Button3);
                break;
            case R.id.task1Button4:
                button = findViewById(R.id.task1Button4);
                break;
            case R.id.task1Button5:
                button = findViewById(R.id.task1Button5);
                break;
            case R.id.task1Button6:
                button = findViewById(R.id.task1Button6);
                break;
            case R.id.task1Button7:
                button = findViewById(R.id.task1Button7);
                break;
            case R.id.task1Button8:
                button = findViewById(R.id.task1Button8);
                break;
            case R.id.task2button1:
                button = findViewById(R.id.task2button1);
                break;
            case R.id.task2button2:
                button = findViewById(R.id.task2button2);
                break;
            case R.id.task2button3:
                button = findViewById(R.id.task2button3);
                break;
            case R.id.task2button4:
                button = findViewById(R.id.task2button4);
                break;
            case R.id.task2button5:
                button = findViewById(R.id.task2button5);
                break;
            case R.id.task2button6:
                button = findViewById(R.id.task2button6);
                break;
            case R.id.task2button7:
                button = findViewById(R.id.task2button7);
                break;
            case R.id.task2button8:
                button = findViewById(R.id.task2button8);
                break;
            default:
                break;
        }

        Toast toast = Toast.makeText(
                getApplicationContext(),
                "\"" + button.getText() + "\" #" + id.toString(),
                Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.d("ActivityLifeCycle", "onCreate called");
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        Log.d("ActivityLifeCycle", "onStart called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("ActivityLifeCycle", "onResume called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("ActivityLifeCycle", "onPause called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("ActivityLifeCycle", "onStop called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("ActivityLifeCycle", "onDestroy called");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("ActivityLifeCycle", "onRestart called");
        super.onRestart();
    }

    public void onImageClick(View view) {
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setProgress(50);

    }

}
