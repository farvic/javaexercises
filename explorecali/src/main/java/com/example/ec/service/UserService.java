package com.example.ec.service;

import com.example.ec.repo.RoleRepository;
import com.example.ec.repo.UserRepository;

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

}