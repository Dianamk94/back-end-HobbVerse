package com.back_end_HobbVerse.HobbVerse.service;

import com.back_end_HobbVerse.HobbVerse.model.Pedido;
import com.back_end_HobbVerse.HobbVerse.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscaPorId(Long id ){
        return pedidoRepository.findById(id);
    }

    public Pedido actualizarPedido(Pedido pedido, Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoActual -> {
                    pedidoActual.setUsuario(pedido.getUsuario());
                    pedidoActual.setProducto(pedido.getProducto());
                    pedidoActual.setEstado(pedido.getEstado());
                    pedidoActual.setFechaPedido(pedido.getFechaEnvio());
                    pedidoActual.setFechaEnvio(pedido.getFechaEnvio());
                    pedidoActual.setTotalPedido(pedido.getTotalPedido());
                    return pedidoRepository.save(pedidoActual);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public void borrarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        pedidoRepository.delete(pedido);
    }

}
