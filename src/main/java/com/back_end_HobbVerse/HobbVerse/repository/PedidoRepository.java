package com.back_end_HobbVerse.HobbVerse.repository;

import com.back_end_HobbVerse.HobbVerse.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
