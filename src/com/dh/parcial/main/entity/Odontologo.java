package com.dh.parcial.main.entity;

public class Odontologo {
    //Atributos
    private int id;
    private int matricula;
    private String nombre;
    private String apellido;

    //Constructor con id
    public Odontologo(int id, int matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //Constructor sin id
    public Odontologo(int matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //Métodos accesores
    public int getId() {
        return id;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    //Método toString que lo utilizó luego cuando quiera imprimir esta información
    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
