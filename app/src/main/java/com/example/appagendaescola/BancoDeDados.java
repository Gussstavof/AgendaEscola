package com.example.appagendaescola;

import  android.content.ContentValues;
import  android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper{
    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_AGENDA = "AgendaEscola";

    public BancoDeDados(@Nullable Context context) {

        super( context, BANCO_AGENDA, null, VERSAO_BANCO );
    }


    public static final String TABELA = "tabela_Materia";

    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_MATERIA = "materia";



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRIAR_TABELA = "CREATE TABLE " + TABELA + "(" + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_DATA + " TEXT,"
                + COLUNA_MATERIA + " TEXT)";

        db.execSQL( CRIAR_TABELA );


    }

    void addMateria(Materia materia){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_DATA, materia.getData());
        valor.put(COLUNA_MATERIA, materia.getMateria());


        db.insert(TABELA, null, valor);
        db.close();
    }

    void apagarMateria(Materia materia) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( TABELA, COLUNA_CODIGO + " =?", new String[]{
                String.valueOf( materia.getCodigo() )} );

        db.close();
    }

    Materia selecionarMateria(int codigo) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query( TABELA, new String[]{
                        COLUNA_CODIGO, COLUNA_DATA, COLUNA_MATERIA},
                COLUNA_CODIGO + " =?", new String[]{String.valueOf( codigo )}, null, null, null, null );

        if (cursor != null) {
            cursor.moveToFirst();
        }


        Materia materia = new Materia( Integer.parseInt( cursor.getString(0)), cursor.getString( 1 ),
                cursor.getString(2));

        return materia;

    }

    void atualizarMateria(Materia materia){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_DATA, materia.getData());
        valor.put(COLUNA_MATERIA, materia.getMateria());


        db.update(TABELA, valor, COLUNA_CODIGO + " = ?",
                new String[]{String.valueOf( materia.getCodigo())});
    }


    public List<Materia> listarMateria() {

        List<Materia> materiaList = new ArrayList<>();

        String query = "SELECT * FROM " + TABELA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery( query, null );

        if (c.moveToFirst()) {
            do {
                Materia materia = new Materia();
                materia.setCodigo( Integer.parseInt( c.getString( 0 ) != null ? c.getString( 0 ) : "0" ) );
                materia.setData( c.getString( 1 ) );
                materia.setMateria( c.getString( 2 ) );


                materiaList.add( materia );

            } while (c.moveToNext());

        }

        return materiaList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

