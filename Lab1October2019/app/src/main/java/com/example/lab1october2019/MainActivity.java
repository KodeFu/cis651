package com.example.lab1october2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void CreateLL(String text) {
        LinearLayout ll = new LinearLayout( this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(0, 5, 0, 0 );
        ll.setWeightSum(3);

        final EditText editText = new EditText(this);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
        editText.setLayoutParams(params);

        Button button = new Button(this);
        LinearLayout.LayoutParams params2 =
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        button.setLayoutParams(params2);
        button.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                CreateLL(editText.getText().toString());
            }
        });

        ll.addView(editText);
        ll.addView(button);
        LinearLayout mainlayout = findViewById(R.id.main_container);
        mainlayout.addView(ll);
    }

    public void AddNew(View view) {
        EditText editText = findViewById(R.id.edit_text);
        CreateLL(editText.getText().toString());
    }
}
