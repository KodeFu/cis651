package com.example.hw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
}
