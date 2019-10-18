package com.example.hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }

    public void onTask2(View view) {
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
            default:
                break;
        }

        Toast toast = Toast.makeText(
                getApplicationContext(),
                "\"" + button.getText() + "\" #" + id.toString(),
                Toast.LENGTH_SHORT);
        toast.show();
    }

}
