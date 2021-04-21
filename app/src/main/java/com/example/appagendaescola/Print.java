package com.example.appagendaescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Print extends AppCompatActivity {
    TextView PrintDisciplina;
    TextView PrintData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        PrintData = findViewById(R.id.vPrintData);
        PrintDisciplina = findViewById(R.id.vPrintDisciplina);

        Intent intentPrint = getIntent();

        String disciplina = intentPrint.getStringExtra("materia");
        String data = intentPrint.getStringExtra("data");

        PrintDisciplina.setText(disciplina);
        PrintData.setText(data);
    }
}