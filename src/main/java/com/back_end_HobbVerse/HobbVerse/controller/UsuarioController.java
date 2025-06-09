package com.back_end_HobbVerse.HobbVerse.controller;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<Usuario> obtenerPorId(@PathVariable Long id){

        return usuarioService.buscarUsuarioId(id);
    }
    /* Post Mapping para guardar un usuario */
    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Usuario guardado con éxito");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/borrar/{id}")
    public  ResponseEntity<String>deleteUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con exito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> actualizarPedido(@RequestBody Usuario usuario, @PathVariable Long id){
        try {
            Usuario actualizado = usuarioService.actualizarUsuario(usuario, id);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
