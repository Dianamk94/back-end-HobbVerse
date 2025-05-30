package com.back_end_HobbVerse.HobbVerse.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotNull
    @Column(name = "nombre_completo", nullable = false, length = 100)
    private  String nombre_completo;

    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private  String email;

    @NotNull
    @Column(name = "telefono", nullable = false, length = 100)
    private  String telefono;

    @NotNull
    @Column(name = "contraseña", nullable = false, length = 100)
    private  String contraseña;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fecha_registro;

    public Usuario(Long id_usuario, String nombre_completo, String email, String telefono, String contraseña, LocalDateTime fecha_registro) {
        this.id_usuario = id_usuario;
        this.nombre_completo = nombre_completo;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.fecha_registro = fecha_registro;
    }

    public Usuario() {
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
