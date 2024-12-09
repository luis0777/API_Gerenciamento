package com.project2.auth_api.dto;

// DTO para transferência de dados do usuário
public class UserDTO {

    private Long id;
    private String login;
    private String email;
    private String password;


    // Construtor vazio necessário para serialização/deserialização
    public UserDTO() {
    }

    // Construtor para fácil inicialização
    public UserDTO(Long id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password= password;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
