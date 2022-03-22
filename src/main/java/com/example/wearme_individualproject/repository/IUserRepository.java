package com.example.wearme_individualproject.repository;

import com.example.wearme_individualproject.logic.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
}
