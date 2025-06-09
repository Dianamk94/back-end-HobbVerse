package com.back_end_HobbVerse.HobbVerse.repository;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IusuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Usuario findByEmailAndContrasena(String email, String contrasena);
}
