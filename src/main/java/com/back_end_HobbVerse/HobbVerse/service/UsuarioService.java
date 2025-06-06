package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.repository.IusuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    private final IusuarioRepository usuarioRepository;


    public UsuarioService(IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioId(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Usuario usuario, Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioActual -> {
                    usuarioActual.setNombreCompleto(usuario.getNombreCompleto());
                    usuarioActual.setContrasena(usuario.getContrasena());
                    usuarioActual.setEmail(usuario.getContrasena());
                    usuarioActual.setTelefono(usuario.getTelefono());
                    usuarioActual.setFechaRegistro(usuario.getFechaRegistro());
                    return usuarioRepository.save(usuarioActual);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }
}

