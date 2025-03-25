package com.example.demo.Empleados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoRepository {

    private final Map<String, Empleado> baseDeDatos = new HashMap<>();

    //Agregar a la base de datos
    public Empleado save(Empleado empleado) {
        baseDeDatos.put(empleado.getId(), empleado);
        return empleado;
    }

    //Eliminar de la base de datos
    public void delete(String idEmpleado) {
        baseDeDatos.remove(idEmpleado);
    }

    //Editar de la base de datos
    public Empleado update(Empleado empleado) {
        if  (baseDeDatos.containsKey(empleado.getId())) {
            baseDeDatos.put(empleado.getId(), empleado);
            return empleado;
        }
        return null;
    }

    //Listar

    public Empleado getById(String idEmpleado) {
        return baseDeDatos.get(idEmpleado);
    }

    public List<Empleado> getAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    public List<Empleado> getByFilters(String nombre, String departamento, int salario, int salarioMin) {
        return baseDeDatos.values().stream()
                .filter(e -> nombre == null || e.getNombre().equals(nombre) )
                .filter(e -> departamento == null || e.getDepartamento().equals(departamento))
                .filter(e -> salario == 0 || e.getSalario() == salario)
                .filter(e -> salarioMin == 0 || e.getSalario() >= salarioMin)
                .collect(Collectors.toList());
    }

}
