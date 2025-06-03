package com.back_end_HobbVerse.HobbVerse.service;
import com.back_end_HobbVerse.HobbVerse.model.Producto;
import com.back_end_HobbVerse.HobbVerse.repository.IproductoRepository; // Importa la interfaz del repositorio
import com.back_end_HobbVerse.HobbVerse.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


 //Implementación de la interfaz IproductoService.

@Service
public class ProductoService implements IproductoService {

    // Inyección de la interfaz del repositorio
    private final IproductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    // Constructor para inyectar la dependencia de IproductoRepository
    @Autowired
    public ProductoService(IproductoRepository productoRepository, PedidoRepository pedidoRepository) {
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    //Llamar al método save() del repositorio

    @Override
    public Producto saveProducto(Producto producto) {
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio del producto debe ser un número positivo.");
        }
        if (producto.getCantidad() == null || producto.getCantidad() < 0) {
            throw new IllegalArgumentException("La cantidad del producto no puede ser negativa.");
        }

        // Creando un nuevo producto o si el nombre ha cambiado en una actualización.
        if (producto.getIdProducto() == null) { // Si es un nuevo producto
            if (productoRepository.existsByNombreProducto(producto.getNombreProducto())) {
                throw new IllegalArgumentException("Ya existe un producto con el nombre: " + producto.getNombreProducto());
            }
        } else {
            Optional<Producto> existingProductOpt = productoRepository.findByNombreProducto(producto.getNombreProducto());
            if (existingProductOpt.isPresent() && !existingProductOpt.get().getIdProducto().equals(producto.getIdProducto())) {
                // Si encontramos un producto con el mismo nombre y no es el mismo producto que estamos actualizando
                throw new IllegalArgumentException("Ya existe otro producto con el nombre: " + producto.getNombreProducto());
            }
        }
        return productoRepository.save(producto);
    }


        @Override
        public Optional<Producto> getProductoById(Long id) {
            return productoRepository.findById(id);
        }

        @Override
        public List<Producto> getAllProductos() {
            return productoRepository.findAll();
        }

        @Override
        public Optional<Producto> getProductoByNombre(String nombre) {
            return productoRepository.findByNombreProducto(nombre);
        }

        @Override
        public List<Producto> getProductosByCategoria(String categoria) {
            return productoRepository.findByCategoria(categoria);
        }

    @Transactional
    public void deleteProducto(Long idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new RuntimeException("Producto con ID " + idProducto + " no encontrado.");
        }

        if (pedidoRepository.existsByProducto_IdProducto(idProducto)) {
            throw new IllegalStateException("No se puede eliminar el producto, está referenciado en pedidos.");
        }

        productoRepository.deleteById(idProducto);
    }

    }