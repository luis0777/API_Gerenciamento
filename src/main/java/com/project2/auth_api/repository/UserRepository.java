package com.project2.auth_api.repository;

import com.project2.auth_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLoginContaining(String partialLogin);
    User findByLogin(String login);
}
