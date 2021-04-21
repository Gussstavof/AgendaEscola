package com.example.appagendaescola;

import  android.content.ContentValues;
import  android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class BancoDeDados extends SQLiteOpenHelper{
    public static  final int VERSAO_BANCO = 1;
    public  static final String BANCO_AGENDA_ESCOLA = "agendaEscola";

    public BancoDeDados(@Nullable Context context){
        super( context, BANCO_AGENDA_ESCOLA, null, VERSAO_BANCO );
    }

    public static final String TABELA_LICAO = "tabelaLicao";
    public static final String COLUNA_MATERIA = "colunaMateria";
    public static final String COLUNA_DATA = "colunaData";
    public static final String ID = "codigo";

    @Override
    public  void  onCreate(SQLiteDatabase db){
        String CRIAR_TABELA = "CREATE TABLE "+ TABELA_LICAO + "(" +  ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT, "
                + COLUNA_MATERIA + " TEXT, " + COLUNA_DATA + " TEXT)";

        db.execSQL(CRIAR_TABELA);

    }

    void addLicao(Materia materia){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_DATA, materia.getData());
        valor.put(COLUNA_MATERIA, materia.getMateria());

        db.insert(TABELA_LICAO, null, valor);
        db.close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
