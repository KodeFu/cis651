package com.example.lab2october2019;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private SeekBar seekBar;
    private TextView textView;
    private ToggleButton toggleButton;
    private Integer oldValue = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seedBar);
        textView = findViewById(R.id.scoretext);
        toggleButton = findViewById(R.id.disable_snack);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        textView.setText(Integer.toString(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        oldValue = seekBar.getProgress();
    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {
        if (toggleButton.isChecked()) {
            Snackbar snackbar = Snackbar.make(seekBar, "Progress Changed", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.setText(oldValue.toString());
                            seekBar.setProgress(oldValue);
                            Snackbar snackbar1 = Snackbar.make(v, "Seekbar Restored", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();
        }
        else {

        }
    }

    public void createToast(View view) {
        RadioButton simpleRb = findViewById(R.id.rb_simple);
        if (simpleRb.isChecked()) {
            Toast.makeText(this, "Simple Toast Message", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toastRoot) );
            SeekBar seekBarInToast = layout.findViewById(R.id.seekBarInToast);
            seekBarInToast.setProgress(seekBar.getProgress());

            TextView textViewInToast = layout.findViewById(R.id.textViewInToast);
            textViewInToast.setText(Integer.toString(seekBar.getProgress()));

            Toast toast = new Toast(this);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }

    public void pickDateTime(View view) {
        final Calendar calender = Calendar.getInstance();
        int yy = calender.get(Calendar.YEAR);
        int mm = calender.get(Calendar.MONTH);
        int dd = calender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, this, yy, mm, dd);
        datePicker.show();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final TextView textView = findViewById(R.id.dateTime);
        String date =  String.valueOf(month) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
        textView.setText(date);

        final Calendar calender = Calendar.getInstance();
        int h = calender.get(Calendar.HOUR_OF_DAY);
        int m = calender.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(this, this, h, m, true);
        timePicker.show();

    }

    public void onTimeSet(TimePicker view, int h, int m) {
        final TextView textView = findViewById(R.id.dateTime);
        String date =  textView.getText() + String.valueOf(h) + ":" + String.valueOf(m);
        textView.setText(date);
    }
}
