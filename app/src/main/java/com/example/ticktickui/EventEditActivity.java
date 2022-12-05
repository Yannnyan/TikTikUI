package com.example.ticktickui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ticktickui.global_variables.GlobalVariables;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{
    private EditText et_pick_up_place;
    private TextView Name, Date, Time;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();

        Bundle extra = getIntent().getExtras();
        time = LocalTime.of(extra.getInt("hour"), extra.getInt("minute"));

        Name.setText(GlobalVariables.name);
        Date.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        Time.setText("Time: " + CalendarUtils.formattedTime(time));


        Button btn_submit = (Button) findViewById(R.id.b_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEventAction(v);
                finish();
            }
        });
    }

    private void initWidgets()
    {
        Name = findViewById(R.id.t_name);
        et_pick_up_place = findViewById(R.id.et_pick_up_place);
        Date = findViewById(R.id.eventDateTV);
        Time = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = et_pick_up_place.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
    }
}