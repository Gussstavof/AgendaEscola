
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
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    CalendarView ViewCalendar;
    TextView mydate;
    EditText editMateria;
    Button save;

    BancoDeDados bd = new BancoDeDados(this);
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
                String data = mydate.getText().toString();
                String materia = editMateria.getText().toString();

                if (materia.isEmpty() ){
                    editMateria.setError("Este campo é obrigatório");
                }
                else {
                    save();
                }
            }
        } );


    }

    private void save() {
        Intent it = new Intent( this, PrintDados.class );
        String data = mydate.getText().toString();
        String materia = editMateria.getText().toString();

        bd.addMateria(new Materia(data, materia));

        Toast.makeText(Calendar.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();
        startActivityForResult( it, 1 );


    }


}