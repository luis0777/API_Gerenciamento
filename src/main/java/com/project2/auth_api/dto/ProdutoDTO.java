package com.project2.auth_api.dto;

import com.project2.auth_api.model.Pedido;

public class ProdutoDTO {

    private  Long id;
    private String nome;
    private double preco;
    private Pedido pedido;

    public ProdutoDTO(Long id, String nome, double preco, Pedido pedido) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.pedido = pedido;
    }

    // Construtor vazio necessário para serialização/deserialização
    public ProdutoDTO(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
