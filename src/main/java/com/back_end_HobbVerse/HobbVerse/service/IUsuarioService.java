package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los servicios disponibles para la entidad Usuario
 */
public interface IUsuarioService {
    
    /**
     * Obtiene todos los usuarios del sistema
     * @return lista de usuarios
     */
    List<Usuario> obtenerTodos();
    
    /**
     * Busca un usuario por su ID
     * @param id el ID del usuario a buscar
     * @return un Optional que contiene el usuario si existe
     */
    Optional<Usuario> buscarUsuarioId(Long id);
    
    /**
     * Crea un nuevo usuario en el sistema
     * @param usuario el usuario a crear
     * @return el usuario creado con su ID asignado
     */
    Usuario crearUsuario(Usuario usuario);
    
    /**
     * Elimina un usuario por su ID
     * @param id el ID del usuario a eliminar
     */
    void eliminarUsuario(Long id);
    
    /**
     * Actualiza los datos de un usuario existente
     * @param usuario los nuevos datos del usuario
     * @param id el ID del usuario a actualizar
     * @return el usuario actualizado
     */
    Usuario actualizarUsuario(Usuario usuario, Long id);
    
    /**
     * Verifica si existe un usuario con el email proporcionado
     * @param email el email a verificar
     * @return true si existe un usuario con ese email, false en caso contrario
     */
    boolean existeEmailUsuario(String email);
    
    /**
     * Autentica un usuario por su email y contraseña
     * @param email el email del usuario
     * @param contrasena la contraseña del usuario
     * @return el usuario autenticado o null si no existe o la contraseña es incorrecta
     */
    Usuario autenticarUsuario(String email, String contrasena);
}