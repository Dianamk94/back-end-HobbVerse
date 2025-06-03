package com.back_end_HobbVerse.HobbVerse.controller;
import com.back_end_HobbVerse.HobbVerse.model.Producto;
import com.back_end_HobbVerse.HobbVerse.service.IproductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

//Controlador Rest: maneja las peticiones HTTP
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    //Inyección de la interfaz productoService
    private final IproductoService productoService;

    //Constructor para inyección IproductoService
    @Autowired
    public ProductoController(IproductoService productoService) {
        this.productoService = productoService;
    }

    //Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.saveProducto(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    //Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint para obtener la lista de productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Endpoint para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Optional<Producto> existingProductoOptional = productoService.getProductoById(id);
        if (existingProductoOptional.isPresent()) {
            Producto productoToUpdate = existingProductoOptional.get();
            productoToUpdate.setNombreProducto(productoDetails.getNombreProducto());
            productoToUpdate.setCantidad(productoDetails.getCantidad());
            productoToUpdate.setPrecio(productoDetails.getPrecio());
            productoToUpdate.setCategoria(productoDetails.getCategoria());
            productoToUpdate.setDescripcion(productoDetails.getDescripcion());

            Producto updatedProducto = productoService.saveProducto(productoToUpdate);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return new ResponseEntity<>("Producto eliminado exitosamente.", HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Error inesperado al eliminar producto: " + e.getMessage());
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Endpoint para buscar un producto por su nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> getProductoByNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.getProductoByNombre(nombre);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint para buscar un producto por su categoria
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.getProductosByCategoria(categoria);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

}
