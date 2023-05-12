package com.example.mualakabashi_simplenewsapi.repository;


import com.example.mualakabashi_simplenewsapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
