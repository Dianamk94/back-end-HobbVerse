package com.back_end_HobbVerse.HobbVerse.repository;

import com.back_end_HobbVerse.HobbVerse.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IproductoRepository extends JpaRepository<Producto, Long> {

    //Busca un producto por su nombre
    Optional<Producto> findByNombreProducto(String nombreProducto);

    //Busca un porducto por su categoria
    List<Producto> findByCategoria(String categoria);

    //Verifica si existe un producto con el nombre especificado
    boolean existsByNombreProducto(String nombreProducto);

}
