package com.project2.auth_api.controller;

import com.project2.auth_api.dto.PedidoDTO;
import com.project2.auth_api.dto.UserDTO;
import com.project2.auth_api.service.PedidoService;
import com.project2.auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    // Cria um novo pedido e retorna o DTO criado
    @PostMapping
    public ResponseEntity<PedidoDTO> createdPedido(@RequestBody PedidoDTO pedidoDTO) {

        PedidoDTO createdPedido = pedidoService.createdPedido(pedidoDTO);
        return ResponseEntity.ok(createdPedido);

    }

    // Pesquisa pedidos pelo usuario
    @GetMapping("/search")
    public ResponseEntity<List<PedidoDTO>> findByUserId(@RequestParam Long user_id) {

        List<PedidoDTO> pedidos = pedidoService.findByUserId(user_id);
        return ResponseEntity.ok(pedidos);

    }


    // Atualiza um pedido existente e retorna o DTO atualizado
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updateUser(@PathVariable Long id, @RequestBody PedidoDTO updatedPedidoDTO) {

        PedidoDTO updatedPedido = pedidoService.updatedPedido(id, updatedPedidoDTO);
        return ResponseEntity.ok(updatedPedido);
    }

    // Remove um pedido específico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 (No Content) em caso de sucesso

    }
}
