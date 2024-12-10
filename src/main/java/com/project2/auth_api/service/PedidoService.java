    package com.project2.auth_api.service;

    import com.project2.auth_api.dto.PedidoDTO;
    import com.project2.auth_api.dto.UserDTO;
    import com.project2.auth_api.model.Pedido;
    import com.project2.auth_api.model.User;
    import com.project2.auth_api.repository.PedidoRepository;
    import com.project2.auth_api.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        // Converte uma entidade User em UserDTO
        private PedidoDTO convertToDTO(Pedido pedido) {
            return new PedidoDTO(pedido.getId(), pedido.getDescricao(), pedido.getUsuario());
        }

        // Converte um UserDTO em uma entidade User
        private Pedido convertToEntity(PedidoDTO pedidoDTO) {
            Pedido pedido = new Pedido();
            pedido.setDescricao(pedidoDTO.getDescricao());
            pedido.setUsuario(pedidoDTO.getUsuario());
            return pedido;
        }

        // Cria um novo pedido e retorna o DTO
        public PedidoDTO createdPedido(PedidoDTO pedidoDTO) {
            validatePedidoDTO(pedidoDTO);
            Pedido pedido = convertToEntity(pedidoDTO);
            Pedido savedPedido = pedidoRepository.save(pedido);
            return convertToDTO(savedPedido);
        }

        // Retorna todos os pedidos
        public List<PedidoDTO> getAllPedidos() {
            return pedidoRepository.findAll().stream()
                    .map(this::convertToDTO) // Converte cada entidade Pedido em PedidoDTO
                    .collect(Collectors.toList());
        }

        // Busca pedido por ID e retorna como DTO
        public PedidoDTO getPedidoById(Long id) {
            System.out.println("[PedidoService] Fetching user by ID: " + id);
            //int i=0;
            //int a= 10/i;
            return pedidoRepository.findById(id)
                    .map(this::convertToDTO)
                    .orElse(null); // Retorna null se o usuário não for encontrado
        }

        // Valida os dados do PedidoDTO antes de criar ou atualizar
        private void validatePedidoDTO(PedidoDTO pedidoDTO) {
            if (pedidoDTO.getDescricao() == null || pedidoDTO.getDescricao().trim().isEmpty()) {
                throw new IllegalArgumentException("O campo 'descricao' não pode estar vazio.");
            }
        }

        // Atualiza um pedido existente e retorna o DTO atualizado
        public PedidoDTO updatedPedido(Long id, PedidoDTO updatedPedidoDTO) {
            validatePedidoDTO(updatedPedidoDTO);

            return pedidoRepository.findById(id)
                    .map(existingPedido -> {
                        existingPedido.setDescricao(updatedPedidoDTO.getDescricao());
                        Pedido savedPedido = pedidoRepository.save(existingPedido);
                        return convertToDTO(savedPedido);
                    })
                    .orElse(null); // Retorna null se o pedido não for encontrado
        }

        // Deleta um pedido por ID
        public boolean deletePedido(Long id) {
            if (pedidoRepository.existsById(id)) {
                pedidoRepository.deleteById(id);
                return true;
            }
            return false; // Retorna false se o pedido não existir
        }

        // Busca pedidos pelo id do user
        public List<PedidoDTO> findByUserId(Long id) {
            return pedidoRepository.findByUser_id(id).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }
    }
