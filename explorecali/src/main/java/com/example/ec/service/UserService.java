package com.example.ec.service;

import com.example.ec.domain.Role;
import com.example.ec.domain.User;
import com.example.ec.repo.RoleRepository;
import com.example.ec.repo.UserRepository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Construct UserService
     *
     * @param userRepository        User Repository
     * @param roleRepository        Role Repository
     * @param authenticationManager Authentication Manager
     * @param passwordEncoder       Password Encoder
     */
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication signin(String username, String password) {
        LOGGER.info("Authenticating with username: {} and password: {}", username, password);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    public Optional<User> signup(String username, String password, String firstName, String lastName) {
        if (!userRepository.findByUsername(username).isPresent()) {
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
            LOGGER.info("{} {} is signing up with username: {}, password: {} and role: {}.", firstName, lastName,
                    username, password, role.get().getRoleName());
            return Optional.of(userRepository.save(new User(username,
                    passwordEncoder.encode(password),
                    role.get(),
                    firstName,
                    lastName)));
        }
        return Optional.empty();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}