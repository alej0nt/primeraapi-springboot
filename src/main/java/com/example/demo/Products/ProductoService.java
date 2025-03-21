package com.example.demo.Products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        initData();
    }

    //Metodo para cargar datos por defecto
    private void initData (){
        productoRepository.save(new Producto("Computador", "Electronica", 10000,10));
        productoRepository.save(new Producto("Aguacate", "Comida", 50000,1));
        productoRepository.save(new Producto("Sofa", "Muebles", 90000, 2));
    }

    //Basicamente esto vendria siendo un conector entre Repository y el controlador

    public Producto save (Producto producto){
        return productoRepository.save(producto);
    }

    public Producto findById(String id){
        return productoRepository.findById(id);
    }
    public List<Producto> findAll(){
        return productoRepository.findAll();
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
