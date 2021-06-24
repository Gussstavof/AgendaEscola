package com.example.appagendaescola;

public class Materia {

    int codigo;
    String data;
    String materia;


    public Materia() {
    }

    public Materia(int codigo, String data, String materia) {
        this.codigo = codigo;
        this.data = data;
        this.materia = materia;
    }

    public Materia(String data, String materia) {
        this.data = data;
        this.materia = materia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

}
