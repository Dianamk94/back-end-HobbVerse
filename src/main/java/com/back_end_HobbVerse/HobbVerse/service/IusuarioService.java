package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;

import java.util.List;

public interface IusuarioService {

    List<Usuario> obtenerTodos();
    Usuario obtenerPorId (Long id);

    void guardarUsuario(Usuario usuario);

    void deleteUsuario(Long id);

    void updateUsuario(Long id, Usuario UsuarioActualizado);

    void editarUsuario(Long id, Usuario UsuarioActualizado);
}
