package com.project2.auth_api.service;

import com.project2.auth_api.dto.PedidoDTO;
import com.project2.auth_api.dto.ProdutoDTO;
import com.project2.auth_api.model.Pedido;
import com.project2.auth_api.model.Produto;
import com.project2.auth_api.repository.PedidoRepository;
import com.project2.auth_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Converte uma entidade User em UserDTO
    private ProdutoDTO convertToDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getPedido());
    }

    // Converte um ProdutoDTO em uma entidade Produto
    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setPedido(produtoDTO.getPedido());
        return produto;
    }

    // Retorna todos os produtos
    public List<ProdutoDTO> getAllProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::convertToDTO) // Converte cada entidade Produto em ProdutoDTO
                .collect(Collectors.toList());
    }

    // Valida os dados do ProdutoDTO antes de criar ou atualizar
    private void validateProdutoDTO(ProdutoDTO produtoDTO) {
        if (produtoDTO.getNome() == null || produtoDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome' não pode estar vazio.");
        }
        if (produtoDTO.getPreco() <0) {
            throw new IllegalArgumentException("O campo 'Preco' não pode ser menor que ZERO.");
        }
    }

    // Cria um novo produto e retorna o DTO
    public ProdutoDTO createdProduto(ProdutoDTO produtoDTO) {
        validateProdutoDTO(produtoDTO);
        Produto produto = convertToEntity(produtoDTO);
        Produto savedProduto = produtoRepository.save(produto);
        return convertToDTO(savedProduto);
    }

    // Busca produtos pelo id do pedido
    public List<ProdutoDTO> findByPedidoId(Long Id) {
        return produtoRepository.findByPedido_id(Id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Atualiza um produto existente e retorna o DTO atualizado
    public ProdutoDTO updatedProduto(Long id, ProdutoDTO updatedProdutoDTO) {
        validateProdutoDTO(updatedProdutoDTO);

        return produtoRepository.findById(id)
                .map(existingProduto -> {
                    existingProduto.setNome(updatedProdutoDTO.getNome());
                    existingProduto.setPreco(updatedProdutoDTO.getPreco());
                    Produto savedProduto = produtoRepository.save(existingProduto);
                    return convertToDTO(savedProduto);
                })
                .orElse(null); // Retorna null se o produto não for encontrado
    }

    // Deleta um produto por ID
    public boolean deleteProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false; // Retorna false se o produto não existir
    }
}