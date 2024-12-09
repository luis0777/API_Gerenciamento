package com.project2.auth_api.controller;

import com.project2.auth_api.dto.PedidoDTO;
import com.project2.auth_api.dto.ProdutoDTO;
import com.project2.auth_api.service.PedidoService;
import com.project2.auth_api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //lista todos os produtos
    @GetMapping
    public List<ProdutoDTO> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    // Cria um novo produto e retorna o DTO criado
    @PostMapping
    public ResponseEntity<ProdutoDTO> createdProduto(@RequestBody ProdutoDTO produtoDTO) {

        ProdutoDTO createdProduto = produtoService.createdProduto(produtoDTO);
        return ResponseEntity.ok(produtoDTO);
    }

    // Pesquisa produtos pelo Pedido
    @GetMapping("/search")
    public ResponseEntity<List<ProdutoDTO>> findByPedidoId(@RequestParam Long pedido_id) {

        List<ProdutoDTO> produtos = produtoService.findByPedidoId(pedido_id);
        return ResponseEntity.ok(produtos);
    }

    // Atualiza um produto existente e retorna o DTO atualizado
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateUser(@PathVariable Long id, @RequestBody ProdutoDTO updatedProdutoDTO) {

        ProdutoDTO updatedProduto = produtoService.updatedProduto(id, updatedProdutoDTO);
        return ResponseEntity.ok(updatedProduto);
    }

    // Remove um produto específico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 (No Content) em caso de sucesso

    }
}
