package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IproductoService {

    Producto saveProducto(Producto producto);

    Optional<Producto> getProductoById(Long id);

    List<Producto> getAllProductos();

    void deleteProducto(Long id);

    Optional<Producto> getProductoByNombre(String nombre);

    List<Producto> getProductosByCategoria(String categoria);

    List<Producto> getProductosByPrecioRango(BigDecimal min, BigDecimal max);
}