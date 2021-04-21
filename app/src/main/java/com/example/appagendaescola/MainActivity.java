package com.example.appagendaescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText email;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.Eemail);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacao(email);

                }



        });
    }

    private boolean validacao(EditText email){
        String emailInput = this.email.getText().toString();

        if (!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "Email valido", Toast.LENGTH_SHORT).show();
            Intent it = new Intent( this,Calendar.class);
            startActivityForResult( it, 1 );
            return true;
        }
        else{
            Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
   
}