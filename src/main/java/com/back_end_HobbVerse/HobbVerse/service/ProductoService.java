package com.back_end_HobbVerse.HobbVerse.service;
import com.back_end_HobbVerse.HobbVerse.model.Producto;
import com.back_end_HobbVerse.HobbVerse.repository.IproductoRepository; // Importa la interfaz del repositorio
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

    // Constructor para inyectar la dependencia de IproductoRepository
    @Autowired
    public ProductoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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
        public void deleteProducto(Long id) {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
            } else {
                // Opcional: Podrías lanzar una excepción más específica aquí
                throw new IllegalArgumentException("Producto con ID " + id + " no encontrado para eliminación.");
            }
        }

        @Override
        public Optional<Producto> getProductoByNombre(String nombre) {
            return productoRepository.findByNombreProducto(nombre);
        }

        @Override
        public List<Producto> getProductosByCategoria(String categoria) {
            return productoRepository.findByCategoria(categoria);
        }

        @Override
        public List<Producto> getProductosByPrecioRango(BigDecimal min, BigDecimal max) {
            return productoRepository.findByPrecioBetween(min, max);
        }
    }