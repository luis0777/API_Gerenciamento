package com.project2.auth_api.repository;

import com.project2.auth_api.model.Pedido;
import com.project2.auth_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByPedido_id(Long id);
}
