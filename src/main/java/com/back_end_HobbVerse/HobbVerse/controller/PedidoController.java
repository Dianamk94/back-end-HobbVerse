package com.back_end_HobbVerse.HobbVerse.controller;

import com.back_end_HobbVerse.HobbVerse.model.Pedido;
import com.back_end_HobbVerse.HobbVerse.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private  final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody  Pedido pedido){
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedido(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id){
        return pedidoService.buscaPorId(id)
                .map(pedido -> ResponseEntity.ok(pedido))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@RequestBody Pedido pedido, @PathVariable Long id){
        try {
            Pedido actualizado = pedidoService.actualizarPedido(pedido, id);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPedido(@PathVariable Long id){
        try {
            pedidoService.borrarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
