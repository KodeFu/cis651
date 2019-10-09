package com.example.week1october2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Clicked(View view){
        Log.d("MainActivity","Button clicked!");
        TextView textView=(TextView) findViewById(R.id.my_text_view);
        textView.setText(R.string.text_text);
        ImageView imageView=(ImageView) findViewById(R.id.my_image_view);
        imageView.setBackgroundResource(R.drawable.flag);
    }
    public void Clicked2(View view){

        TextView textView=(TextView) findViewById(R.id.my_text_view);
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
        {
            textView.setText(R.string.warning);
        }
        else
        {
            textView.setText(R.string.text_text2);
        }
    }
}
