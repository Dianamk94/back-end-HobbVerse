package com.back_end_HobbVerse.HobbVerse.controller;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;
import com.back_end_HobbVerse.HobbVerse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Usuario> listaUsuario() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioId(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok("Usuario guardado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        try {
            Usuario actualizado = usuarioService.actualizarUsuario(usuario, id);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
                response.put("success", false);
                response.put("error", "El email es requerido");
                return ResponseEntity.badRequest().body(response);
            }

            if (usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()) {
                response.put("success", false);
                response.put("error", "La contraseña es requerida");
                return ResponseEntity.badRequest().body(response);
            }

            if (usuarioService.existeEmailUsuario(usuario.getEmail())) {
                response.put("success", false);
                response.put("error", "Este correo ya está registrado");
                return ResponseEntity.badRequest().body(response);
            }

            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);

            response.put("success", true);
            response.put("mensaje", "Usuario registrado exitosamente");

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", nuevoUsuario.getId());
            userInfo.put("nombre", nuevoUsuario.getNombreCompleto());
            userInfo.put("email", nuevoUsuario.getEmail());
            userInfo.put("telefono", nuevoUsuario.getTelefono());

            response.put("usuario", userInfo);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUsuario(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();

        try {
            String email = credentials.get("email");
            String contrasena = credentials.get("contrasena");

            if (email == null || contrasena == null) {
                response.put("success", false);
                response.put("error", "Email y contraseña son requeridos");
                return ResponseEntity.badRequest().body(response);
            }

            if ("AdminHobb@hobbverse.com".equals(email) && "HobbAdmin1".equals(contrasena)) {
                response.put("success", true);
                response.put("message", "Login exitoso");

                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("email", email);
                userInfo.put("nombre", "Administrador");
                userInfo.put("isAdmin", true);

                response.put("usuario", userInfo);
                return ResponseEntity.ok(response);
            }

            Usuario usuario = usuarioService.autenticarUsuario(email, contrasena);

            if (usuario != null) {
                response.put("success", true);
                response.put("message", "Login exitoso");

                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("id", usuario.getId());
                userInfo.put("nombre", usuario.getNombreCompleto());
                userInfo.put("email", usuario.getEmail());
                userInfo.put("telefono", usuario.getTelefono());
                userInfo.put("isAdmin", false);

                response.put("usuario", userInfo);
                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("error", "Credenciales incorrectas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}