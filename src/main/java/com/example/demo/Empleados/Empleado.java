package com.example.demo.Empleados;

import java.util.UUID;

public class Empleado {

    private String nombre;
    private String departamento;
    private String cargo;
    private int salario;
    private String id;

    public Empleado(String nombre, String departamento, String cargo, int salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.cargo = cargo;
        this.salario = salario;
        this.id = UUID.randomUUID().toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
