package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
