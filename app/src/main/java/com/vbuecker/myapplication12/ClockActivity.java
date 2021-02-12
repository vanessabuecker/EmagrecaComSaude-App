package com.vbuecker.myapplication12;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClockActivity extends AppCompatActivity {
    private Button btn_notify;
    private EditText editMinutes;
    private TimePicker timePicker;

    private int minute;
    private int hour;
    private int interval;

    private boolean activated = false;
    private SharedPreferences preferences;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        btn_notify = findViewById(R.id.btn_notify);
        editMinutes = findViewById(R.id.edit_txt_number_intervalo);
        timePicker = findViewById(R.id.time_picker);

        timePicker.setIs24HourView(true);
        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);

        activated = preferences.getBoolean("activated", false);

        if (activated) {
            btn_notify.setText("Pausar");
            int color = ContextCompat.getColor(this, R.color.azul);
            btn_notify.setBackgroundTintList(ColorStateList.valueOf(color));

            int interval = preferences.getInt("interval", 0);
            int hour = preferences.getInt("hour", timePicker.getCurrentHour());
            int minute = preferences.getInt("minute", timePicker.getCurrentMinute());

            editMinutes.setText(String.valueOf(interval));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void notifyClick(boolean view) {
        String sInterval = editMinutes.getText().toString();

        if (sInterval.isEmpty()) {
            Toast.makeText(this, "Digite um intervalo", Toast.LENGTH_LONG).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);

        if (!activated) {
            btn_notify.setText("Pausar");
            int color = ContextCompat.getColor(this, R.color.azul);
            btn_notify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.azul));
            activated = true;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();

        } else {
            btn_notify.setText("Notificar");
            int color = ContextCompat.getColor(this, R.color.azul_00);
            btn_notify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.azul_00));
            activated = false;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", false);
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minute");
            editor.apply();
        }
    }
}