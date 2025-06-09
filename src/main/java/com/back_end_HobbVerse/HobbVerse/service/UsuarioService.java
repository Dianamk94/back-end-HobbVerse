package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.repository.IusuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    private final IusuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarUsuarioId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario, Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario actualizado = usuarioExistente.get();
            actualizado.setNombreCompleto(usuario.getNombreCompleto());
            actualizado.setEmail(usuario.getEmail());
            actualizado.setTelefono(usuario.getTelefono());

            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                actualizado.setContrasena(usuario.getContrasena());
            }
            return usuarioRepository.save(actualizado);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public boolean existeEmailUsuario(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Usuario autenticarUsuario(String email, String contrasena) {
        return usuarioRepository.findByEmailAndContrasena(email, contrasena);
    }
}