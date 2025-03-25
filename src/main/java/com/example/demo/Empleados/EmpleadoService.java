package com.example.demo.Empleados;

import java.util.List;

import com.example.demo.Products.ProductoRepository;
import com.example.demo.Users.Usuario;
import com.example.demo.Users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, UsuarioRepository usuarioRepository) {
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
        initData();
    }

    private void initData (){
        empleadoRepository.save(new Empleado("Juan", "Ventas", "Jefe", 30000));
        empleadoRepository.save(new Empleado("Jose", "Ventas", "Empleado", 20000));
        empleadoRepository.save(new Empleado("Daniela", "Marketing", "Jefe", 30000));
    }
    //Save

    public Empleado save(String authToken, Empleado empleado) {
        Usuario user = usuarioRepository.findByAuthToken(authToken);
        if (user != null && user.getRole().equals("Admin")) {
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    //Delete

    public void delete(String id) {
        empleadoRepository.delete(id);
    }

    //Update

    public Empleado update(Empleado empleado) {
        return empleadoRepository.update(empleado);
    }

    //Listar

    public List<Empleado> findAll() {
        return empleadoRepository.getAll();
    }

    public List<Empleado> findAllAuth(String authToken) {
        Usuario user = usuarioRepository.findByAuthToken(authToken);
        if (user != null) {
            empleadoRepository.getAll();
        }
        return null;
    }


    public Empleado findById(String id) {
        return empleadoRepository.getById(id);
    }

    public List<Empleado> findByFilters(String nombre, String departamento, int salario, int salarioMin) {
        return empleadoRepository.getByFilters(nombre, departamento, salario, salarioMin);
    }
}
