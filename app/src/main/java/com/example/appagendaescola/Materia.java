package com.example.appagendaescola;

public class Materia {

    int codigo;
    String materia;
    String data;

    public Materia(){

    }

    public Materia(int codigo, String materia, String data) {
        this.codigo = codigo;
        this.materia = materia;
        this.data = data;
    }

    public Materia(int parseInt, String string, String string1, String string2) {
    }

    public  int getCodigo(){
        return codigo;
    }

    public  void  setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getMateria(){
        return materia;
    }

    public void setMateria(String materia){
        this.materia = materia;
    }

    public String getData(){
        return data;
    }

    public void  setData(String data){
        this.data = data;
    }
}
