package com.example.appagendaescola;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {
    CalendarView ViewCalendar;
    TextView mydate;
    EditText editMateria;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        editMateria = findViewById(R.id.editMateria);
        ViewCalendar = (CalendarView) findViewById(R.id.ViewCalendar);
        mydate =findViewById(R.id.mydate);
        save = findViewById(R.id.saveButton);

        ViewCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int i, int i1,  int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                mydate.setText(date);
            }
        });

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                print();
            }
        } );


    }

    private void print() {
        Intent it = new Intent( this, Print.class );
        it.putExtra("materia", editMateria.getText().toString());
        it.putExtra("data", mydate.getText().toString());
        startActivityForResult( it, 1 );


    }
}