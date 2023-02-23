package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.UserRepository;
import com.helloworld.hello_world.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User validateCredentials(User user) {
        return userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail())
                .filter(u -> bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword()))
                .orElse(null);
    }
}
