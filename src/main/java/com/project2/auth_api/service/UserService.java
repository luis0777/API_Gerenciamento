package com.project2.auth_api.service;

import com.project2.auth_api.dto.UserDTO;
import com.project2.auth_api.model.User;
import com.project2.auth_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO login(String login, String senha) {
        // Verifica se o usuário existe com o login fornecido
        User user = userRepository.findByLogin(login);

        // Verifica se o usuário foi encontrado e se a senha corresponde
        if (user != null && user.getPassword().equals(senha)) {
            // Retorna o UserDTO se o login for bem-sucedido
            return new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getPassword());
        }

        // Se o login ou senha estiverem incorretos, retorna null
        return null;
    }



    // Converte uma entidade User em UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getPassword());
    }

    // Converte um UserDTO em uma entidade User
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    // Valida os dados do UserDTO antes de criar ou atualizar
    private void validateUserDTO(UserDTO userDTO) {
        if (userDTO.getLogin() == null || userDTO.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'login' não pode estar vazio.");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'email' não pode estar vazio.");
        }
        if(userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()){
            throw new IllegalArgumentException("O campo 'password' não pode estar vazio.");
        }
    }

    // Retorna todos os usuários como DTOs
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO) // Converte cada entidade User em UserDTO
                .collect(Collectors.toList());
    }

    // Busca usuário por ID e retorna como DTO
    public UserDTO getUserById(Long id) {
        System.out.println("[UserService] Fetching user by ID: " + id);
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null); // Retorna null se o usuário não for encontrado
    }

    // Cria um novo usuário e retorna o DTO
    public UserDTO createUser(UserDTO userDTO) {
        validateUserDTO(userDTO);
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    // Atualiza um usuário existente e retorna o DTO atualizado
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        validateUserDTO(updatedUserDTO);

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setLogin(updatedUserDTO.getLogin());
                    existingUser.setEmail(updatedUserDTO.getEmail());
                    User savedUser = userRepository.save(existingUser);
                    return convertToDTO(savedUser);
                })
                .orElse(null); // Retorna null se o usuário não for encontrado
    }

    // Deleta um usuário por ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // Retorna false se o usuário não existir
    }

    // Busca usuários contendo parte do login e retorna como lista de DTOs
    public List<UserDTO> searchUsersByLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("O parâmetro 'login' não pode estar vazio.");
        }
        return userRepository.findByLoginContaining(login).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
