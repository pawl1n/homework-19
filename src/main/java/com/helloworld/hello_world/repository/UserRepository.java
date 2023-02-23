package com.helloworld.hello_world.repository;

import com.helloworld.hello_world.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameOrEmail(String username, String email);
}
