package com.project2.auth_api.dto;

import com.project2.auth_api.model.User;

public class PedidoDTO {

    private  Long id;
    private String descricao;
    private User usuario;

    public PedidoDTO(Long id, String descricao, User usuario) {
        this.id = id;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    // Construtor vazio necessário para serialização/deserialização
    public PedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

}
