package com.project2.auth_api.repository;

import com.project2.auth_api.model.Pedido;
import com.project2.auth_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUser_id(Long id);
}
