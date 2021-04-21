package com.example.appagendaescola;

public class Materia {

    int codigo;
    String materia;
    String data;

    public Materia(){

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
