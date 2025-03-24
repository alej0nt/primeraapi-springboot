package com.example.demo.Products;

import java.util.List;
import com.example.demo.Users.Usuario;
import com.example.demo.Users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public ProductoService(ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        initData();
    }

    //Metodo para cargar datos por defecto
    private void initData (){
        productoRepository.save(new Producto("Computador", "Electronica", 10000,10));
        productoRepository.save(new Producto("Aguacate", "Comida", 50000,1));
        productoRepository.save(new Producto("Sofa", "Muebles", 90000, 2));
    }

    //Basicamente esto vendria siendo un conector entre Repository y el controlador

    public Producto save (String authToken, String role, Producto producto){
        Usuario usuario = usuarioRepository.findByAuthToken(authToken);
        if (usuario != null && usuario.getRole().equals(role)){
            return productoRepository.save(producto);
        }
        return null;
    }

    public Producto findById(String authToken, String id){
        Usuario user = usuarioRepository.findByAuthToken(authToken);
        if  (user != null){
            return productoRepository.findById(id);
        }
        return null;
    }

    public void removeById(String id){
        productoRepository.deleteById(id);
    }

    public Producto update(Producto producto){
        return productoRepository.update(producto);
    }
    // Buscar usuarios por filtros
    public List<Producto> buscarPorFiltros(String nombre, String categoria, int precio, int stock) {
        return productoRepository.buscarPorFiltros(nombre, categoria, precio, stock);
    }
}
