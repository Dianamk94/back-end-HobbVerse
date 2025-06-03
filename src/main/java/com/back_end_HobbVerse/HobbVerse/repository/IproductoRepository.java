package com.back_end_HobbVerse.HobbVerse.repository;

import com.back_end_HobbVerse.HobbVerse.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IproductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPrecioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
    boolean existsByNombreProducto(String nombreProducto);
}
