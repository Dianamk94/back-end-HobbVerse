package com.back_end_HobbVerse.HobbVerse.controller;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listaUsuario(){
        return  usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id){
        return usuarioService.obtenerPorId(id);
    }
    /* Post Mapping para guardar un usuario */
    @PostMapping
    public ResponseEntity<String>guardarUsuario(@RequestBody Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok("Usuario guardado con exito");
    }

    @DeleteMapping("/borrar/{id}")
    public  ResponseEntity<String>deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con exito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String>editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado){
        usuarioService.editarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok("Usuario actualizado con exito");
    }

}
