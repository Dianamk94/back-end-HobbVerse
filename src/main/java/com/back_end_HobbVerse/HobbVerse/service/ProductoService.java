package com.back_end_HobbVerse.HobbVerse.service;
import com.back_end_HobbVerse.HobbVerse.model.Producto;
import com.back_end_HobbVerse.HobbVerse.repository.IproductoRepository; // Importa la interfaz del repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    // Inyección de la interfaz del repositorio
    private final IproductoRepository productoRepository;

    public ProductoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }

    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProductoId(Long id){
        return productoRepository.findById(id);
    }

    public Producto actualizarProducto(Producto producto, Long id) {
        return productoRepository.findById(id)
                .map(productoActual -> {
                    productoActual.setNombreProducto(producto.getNombreProducto());
                    productoActual.setCantidad(producto.getCantidad());
                    productoActual.setCategoria(producto.getCategoria());
                    productoActual.setDescripcion(producto.getDescripcion());
                    productoActual.setPrecio(producto.getPrecio());
                    return productoRepository.save(productoActual);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public List<Producto> buscarPorCategoria(String categoria) {

        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombre);
    }

    public boolean buscarPorNombreEspecifico(String nombre){
        return productoRepository.existsByNombreProducto(nombre);
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        productoRepository.delete(producto);
    }


    }