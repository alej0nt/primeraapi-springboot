package com.example.demo.Empleados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Empleado>> findAll() {
        List<Empleado> empleados = empleadoService.findAll();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping ("/departamento/{nombreDepartamento}")
    public ResponseEntity<List<Empleado>> findByDepartamento(@PathVariable String nombreDepartamento, @RequestParam(required = false) String nombre,
                                                             @RequestParam(defaultValue = "0") int salario, @RequestParam(defaultValue = "0") int salarioMin) {
        List<Empleado> empleados = empleadoService.findByFilters(nombre, nombreDepartamento, salario, salarioMin);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable String id) {
        Empleado empleado = empleadoService.findById(id);
        if (empleado != null) {
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/auth")
    public  ResponseEntity<List<Empleado>> findAll(@RequestHeader ("Bearer-token")  String token) {
        List<Empleado> empleados = empleadoService.findAllAuth(token);
        if (empleados != null) {
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //REMOVE

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        Empleado empleado = empleadoService.findById(id);
        if (empleado != null) {
            empleadoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //POST

    @PostMapping
    public ResponseEntity<Empleado> save(@RequestHeader("X-Admin-Token") String adminToken, @RequestBody Empleado empleado) {
        Empleado newEmpleado =  empleadoService.save(adminToken, empleado);
        if  (newEmpleado != null) {
            return new ResponseEntity<>(newEmpleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    //PUT

    @PutMapping ("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable String id, @RequestBody Empleado empleado) {
        Empleado empleadoUpdate = empleadoService.findById(id);
        if (empleadoUpdate != null) {
            empleado.setId(id);
            Empleado newEmpleado = empleadoService.update(empleado);
            return new ResponseEntity<>(newEmpleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
