package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.repository.IusuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService implements  IusuarioService{

    private final IusuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IusuarioRepository iusuarioRepository, IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);

    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void updateUsuario(Long id, Usuario usuarioActualizado) {

    }

    @Override
    public void editarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistete = usuarioRepository.findById(id).orElse(null);

        if(usuarioExistete != null){

            usuarioExistete.setNombre_completo(usuarioActualizado.getNombre_completo());
            usuarioExistete.setTelefono(usuarioActualizado.getTelefono());
            usuarioExistete.setEmail(usuarioActualizado.getEmail());
            usuarioExistete.setContraseña(usuarioActualizado.getContraseña());
            usuarioExistete.setFecha_registro(usuarioActualizado.getFecha_registro());

            usuarioRepository.save(usuarioExistete);
        } else{
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }
    }
}
