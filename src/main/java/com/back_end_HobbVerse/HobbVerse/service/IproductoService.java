package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IproductoService {

    //Crear y guardar un nuevo producto o lo actualiza
    Producto saveProducto(Producto producto);

    //Obtiene un producto por su ID
    Optional<Producto> getProductoById(Long id);

    //Obtiene l alista de productos
    List<Producto> getAllProductos();

    //Elimina un producto por su ID
    void deleteProducto(Long id);

    //Obtiene un producto por su nombre
    Optional<Producto> getProductoByNombre(String nombre);

    //Obtiene un producto por su categoria
    List<Producto> getProductosByCategoria(String categoria);



}