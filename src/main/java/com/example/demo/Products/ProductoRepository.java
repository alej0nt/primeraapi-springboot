package com.example.demo.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {
    // Simulamos una base de datos con un Map
    private final Map<String, Producto> baseDeDatos = new HashMap<>();
   

    //Metodo de save
    public Producto save (Producto p) {
        //Guardamos el producto en la base de datos simulada (El metodo put sirve para esto)
        baseDeDatos.put(p.getId(), p);
        return p;
    }

    //Encontrar usuario por id

    public Producto findById (String id) {
        //Devuelve el usuario asociado a la id que esta en la base de datos simulada
        return baseDeDatos.get(id);
    }

    //Listar todos los usuarios

    public List<Producto> findAll() {
        /*Hace una colección con los datos de la base de datos
        despues, esta coleccion se añade a un arrayList que se retorna
         */
        return new ArrayList<>(baseDeDatos.values());
    }

    //Eliminar por id
    public void deleteById (String id) {
        //Se elimina el producto que encaje con la id que hay en nuestra base de datos simulada
        baseDeDatos.remove(id);
    }

    //Update

    public Producto update (Producto p) {
        //Validamos si contiene la id del producto con contains
        if(baseDeDatos.containsKey(p.getId())) {
            //Si la tiene, usamos el metodo put
            baseDeDatos.put(p.getId(), p);
            //Retornamos el producto
            return p;
        }
        //Retornamos null si este producto no existe
        return null;
    }
    // Buscar usuarios por filtros
    public List<Producto> buscarPorFiltros(String nombre, String categoria, int precio, int stock){
        //Se obtiene un stream de la base de datos y esta se filtra segun lo q necesitemos
        return baseDeDatos.values().stream()
                //Si la variable por la que queremos filtrar es == a null , se retornaran todos los datos, si no es null, se retornan los datos que tengan la variable que querermos filtrar
                //Ejemplo, si quiero filtrar por categoria "Electronica" y no hay esta categoria, retornaran todos los datos del map
                .filter(u -> nombre == null || u.getNombre().contains(nombre))
                .filter(u -> categoria == null || u.getCategoria().contains(categoria))
                .filter(u -> stock == 0 || u.getPrecio() == precio)
                .filter(u -> stock == 0 || u.getStock() == stock)
                //Esto convierte el stream a una lista
                .collect(Collectors.toList());
    }
}
