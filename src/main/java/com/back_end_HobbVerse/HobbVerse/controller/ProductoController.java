package com.back_end_HobbVerse.HobbVerse.controller;
import com.back_end_HobbVerse.HobbVerse.model.Producto;
import com.back_end_HobbVerse.HobbVerse.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//Controlador Rest: maneja las peticiones HTTP
@RestController
@RequestMapping("/productos")
public class ProductoController {

    //Inyección de la interfaz productoService
    private final ProductoService productoService;

    //Constructor para inyección IproductoService
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    //Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarProductoId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint para obtener la lista de productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.obtenerTodos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Endpoint para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        try {
            Producto updatedProducto = productoService.actualizarProducto(productoDetails, id);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/coincidencias/{nombre}")
    public ResponseEntity<List<Producto>> getProductosByNombre(@PathVariable String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }


    //Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.buscarProductoId(id).isPresent()) {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint para buscar un producto por su nombre
    @GetMapping("/existe/nombre/{nombre}")
    public ResponseEntity<Boolean> existeProductoPorNombreExacto(@PathVariable String nombre) {
        boolean existe = productoService.buscarPorNombreEspecifico(nombre);
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }


    //Endpoint para buscar un producto por su categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.buscarPorCategoria(categoria);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

}
