package com.example.ec.service;

import com.example.ec.domain.Role;
import com.example.ec.domain.User;
import com.example.ec.repo.RoleRepository;
import com.example.ec.repo.UserRepository;
import com.example.ec.security.JwtProvider;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    /**
     * Construct UserService
     *
     * @param userRepository        User Repository
     * @param roleRepository        Role Repository
     * @param authenticationManager Authentication Manager
     * @param passwordEncoder       Password Encoder
     * @param jwtProvider           JWT Provider
     */

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public Optional<String> signin(String username, String password) {
        LOGGER.info("Authenticating with username: {} and password: {}", username, password);
        Optional<String> token = Optional.empty();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwtProvider.createToken(username, user.get().getRoles()));
            } catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", username);
            }
        }
        return token;

    }

    public Optional<User> signup(String username, String password, String firstName, String lastName) {
        LOGGER.info("New user attempting to sign in");
        Optional<User> user = Optional.empty();
        if (!userRepository.findByUsername(username).isPresent()) {
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
            LOGGER.info("{} {} is signing up with username: {}, password: {} and role: {}.", firstName, lastName,
                    username, password, role.get().getRoleName());
            user = Optional.of(userRepository.save(new User(username,
                    passwordEncoder.encode(password),
                    role.get(),
                    firstName,
                    lastName)));
        }
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}