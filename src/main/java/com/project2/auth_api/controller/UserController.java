package com.project2.auth_api.controller;

import com.project2.auth_api.dto.UserDTO;
import com.project2.auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // Retorna todos os usuários como DTOs
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Retorna um usuário específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO); // Retorna com status HTTP 200 (OK)
    }

    // Cria um novo usuário e retorna o DTO criado
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.ok(createdUser);
        
    }

    // Atualiza um usuário existente e retorna o DTO atualizado
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        
            UserDTO updatedUser = userService.updateUser(id, updatedUserDTO);
            return ResponseEntity.ok(updatedUser);
        
    }

    // Remove um usuário específico pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // Retorna HTTP 204 (No Content) em caso de sucesso
       
    }

    // Pesquisa usuários pelo login
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsersByLogin(@RequestParam String login) {
        
            List<UserDTO> users = userService.searchUsersByLogin(login);
            return ResponseEntity.ok(users);
        
    }

    // Login do usuario
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO loginDTO) {
        UserDTO userDTO = userService.login(loginDTO.getLogin(), loginDTO.getPassword());

        if (userDTO == null) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        return ResponseEntity.ok(userDTO);
    }
}
