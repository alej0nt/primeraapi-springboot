package com.example.demo.Products;

import java.util.List;
import com.example.demo.Users.Usuario;
import com.example.demo.Users.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//Aqui creamos nuestra ruta de la api
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    @Autowired
    public ProductoController(ProductoService productoService, UsuarioService usuarioService) {
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    //Metodo para listar todos los usuarios

    //Usamos getmapping para definir el endpoint para mostrar los usuarios
    @GetMapping
    //Creamos el metodo que retornara una respuesta junto con una lista con los productos de la base de datos
    public ResponseEntity<List<Producto>> findAll(@RequestParam(required = false) String nombre,
                                                  @RequestParam(required = false) String categoria, @RequestParam(defaultValue = "0") int precio, @RequestParam(defaultValue = "0") int stock) {
        //Llenamos una lista de productos con el metodo findAll (Que también devuelve una lista de productos)
        List<Producto> productos = productoService.buscarPorFiltros(nombre, categoria, precio, stock);
        //Retornamos la entidad de respuesta con nuestra lista y con el estado OK
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Metodo para guardar datos

    //Usamos getmapping /{id} para definir el endpoint para obtener un usuario por id
    @GetMapping("/{id}")
    /*Usamos el PathVariable para obtener el String de la url
     Igual que el metodo anterior, usaremos un ResponseEntity, pero esta vez lo que devolveremos es solo el producto que nos interesa*/
    public ResponseEntity<Producto> findById(@RequestHeader("Authorization") String Authtoken, @PathVariable String id) {
        //Buscamos nuestro producto con el metodo que hicimos en repository
        Producto producto = productoService.findById(Authtoken, id);
        //Validamos que exista
        //Si existe
        if (producto != null ) {
            //Si existe retornamos el producto y el OK (200)
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        //Si no existe retornamos NOT_FOUND (404)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Metodo para crear un usuario

    //Pediremos un postmapping para crear el usuario en el endpoint que ya tenemos
    @PostMapping
    /*Retornaremos un ResponseEntity con el producto que crearemos.
    A su vez, le pediremos un Body para crear el producto*/
    public ResponseEntity<Producto> create(@RequestHeader("Authorization") String authToken, @RequestHeader("X-user-role") String role, @RequestBody Producto producto) {
        //Le asignaremos el producto a una variable
        Producto newProducto = productoService.save(authToken, role, producto);
        //Devolveremos el producto y el status CREATED (201)
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    //Metodo para actualizar un usuario

    //Pediremos un put mapping ya que necesitamos el endpoint /{id} para saber que user actualizaremos
    @PutMapping ("/{id}")
    //Ahora, pediremos el path variable para sacar el id de la url y usaremos request body para pedir el body para actualizar el producto
    public ResponseEntity<Producto> update( @RequestHeader("Authorization") String authTok, @PathVariable String id, @RequestBody Producto producto) {
        //Buscaremos nuestro producto
        Producto newProducto = productoService.findById(authTok, producto.getId());
        //Validamos si existe
        //Si existe
        if (newProducto != null) {
            //A el producto le pondremos la id
            producto.setId(id);
            //Actualizaremos el producto
            Producto updatedProducto = productoService.update(producto);
            //Y ahora retornaremos el response entity con nuestro usuario actualizado y con el status OK (200)
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        }
        //Si no existe (404)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Metodo de eliminar

    //Usaremos el deleteMapping /{id} para encontrar el endpoint en el que sacaremos la id del producto a eliminar
    @DeleteMapping("/{id}")
    //Sacaremos el id de la url
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authTok, @PathVariable String id) {
        //Miraremos si el producto existe
        Producto producto = productoService.findById(authTok, id);
        //Si existe
        if (producto != null) {
            //Removeremos nuestro producto
            productoService.removeById(id);
            //Retornaremos un NO_CONTENT (204)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Si no existe
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
