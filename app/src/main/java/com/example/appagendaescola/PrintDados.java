package com.example.appagendaescola;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class PrintDados extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;

    BancoDeDados bd = new BancoDeDados(this);


    TextView agenda;
    EditText txcodigo;
    EditText txdata;
    EditText txmateria;
    LinearLayout pageDown;


    Button btnLimpar, btnSalvar, btnExcluir, btnVoltar;

    ListView listViewMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_dados);

        agenda = findViewById(R.id.AGENDA);
        txcodigo = findViewById(R.id.edit_codigo);
        txdata = findViewById(R.id.edit_data);
        txmateria = findViewById(R.id.edit_materia);


        listViewMateria = (ListView) findViewById(R.id.listViewMateria);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        pageDown = (LinearLayout) findViewById(R.id.pageDown);

        imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);


        listViewMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String conteudo = (String) listViewMateria.getItemAtPosition(position);

                String codigo = conteudo.substring(0, conteudo.indexOf(" - "));

                Materia materia = bd.selecionarMateria(Integer.parseInt(codigo));

                txcodigo.setText(String.valueOf(materia.getCodigo()));
                txdata.setText(materia.getData());
                txmateria.setText(materia.getMateria());
                changeVisible();




            }
        });

        listarMateria();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txcodigo.getText().toString();
                String data = txdata.getText().toString();
                String materia = txmateria.getText().toString();


                if (data.isEmpty() && materia.isEmpty()) {
                    txmateria.setError("Este campo é obrigatório");
                } else if (codigo.isEmpty()) {

                    bd.addMateria(new Materia(data, materia));

                    Toast.makeText(PrintDados.this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show();

                    listarMateria();
                    limpaCampos();
                    changeGone();


                } else {
                    bd.atualizarMateria(new Materia(Integer.parseInt(codigo), data, materia));

                    Toast.makeText(PrintDados.this, "Atualização feita com sucesso!! ", Toast.LENGTH_SHORT).show();

                    listarMateria();
                    limpaCampos();
                    changeGone();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });



        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = txcodigo.getText().toString();
                if (codigo.isEmpty()) {

                    Toast.makeText(PrintDados.this, "Preencha todos os campos!!", Toast.LENGTH_LONG).show();


                } else {

                    Materia materia = new Materia();
                    materia.setCodigo(Integer.parseInt(codigo));
                    bd.apagarMateria(materia);

                    Toast.makeText(PrintDados.this, "Cadastro pessoa apagado com sucesso!!!", Toast.LENGTH_LONG).show();

                    listarMateria();
                    limpaCampos();
                    changeGone();
                }
            }
        });


    }


    public void listarMateria() {

        List<Materia> materia = bd.listarMateria();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(PrintDados.this, android.R.layout.simple_list_item_1, arrayList);

        listViewMateria.setAdapter(adapter);

        for (Materia c: materia) {

            arrayList.add(c.getCodigo() + " - " + c.getMateria());

            adapter.notifyDataSetChanged();
        }
    }


    void limpaCampos() {
        txcodigo.setText("");
        txdata.setText("");
        txmateria.setText("");


    }
    private void changeVisible(){
        pageDown.setVisibility(View.VISIBLE);
    }
    private void changeGone(){
        pageDown.setVisibility(View.GONE);
    }
    private void voltar() {
        Intent it = new Intent( this, Calendar.class );
        startActivityForResult( it, 1 );


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                agenda.setText(data.getStringExtra("Volta"));

            }
        }

    }
}




