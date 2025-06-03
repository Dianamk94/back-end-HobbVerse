package com.back_end_HobbVerse.HobbVerse.model;

import com.back_end_HobbVerse.HobbVerse.model.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
    private Producto producto;

    @NotNull
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @NotNull
    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;

    @NotNull
    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @NotNull
    @Column(name = "total_pedido", nullable = false)
    private double totalPedido;

    public Pedido() {}

    public Pedido(Usuario usuario, Producto producto, LocalDateTime fechaPedido, LocalDateTime fechaEnvio, String estado, double totalPedido) {
        this.usuario = usuario;
        this.producto = producto;
        this.fechaPedido = fechaPedido;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
        this.totalPedido = totalPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalPedido() {
        return totalPedido;
    }
    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

}
