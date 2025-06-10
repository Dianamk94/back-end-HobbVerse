package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.repository.IusuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{

    private final IusuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UsuarioService(IusuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario){
        String passEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passEncriptada);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioId(Long id){
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Usuario usuario, Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioActual -> {
                    usuarioActual.setNombreCompleto(usuario.getNombreCompleto());

                    if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                        String passEncriptada = passwordEncoder.encode(usuario.getContrasena());
                        usuarioActual.setContrasena(passEncriptada);
                    }

                    usuarioActual.setEmail(usuario.getEmail());
                    usuarioActual.setTelefono(usuario.getTelefono());
                    usuarioActual.setFechaRegistro(usuario.getFechaRegistro());

                    return usuarioRepository.save(usuarioActual);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }


    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }
}

