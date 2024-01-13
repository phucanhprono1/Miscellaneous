package com.example.miscellaneous.controller.auth;

import com.example.miscellaneous.model.Customer;
import com.example.miscellaneous.model.User;
import com.example.miscellaneous.repository.CustomerRepository;
import com.example.miscellaneous.repository.UserRepository;
import com.example.miscellaneous.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        Customer customer = new Customer();
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setGender(request.getGender());
        user.setUser_type("CUSTOMER");
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        user = userRepository.save(user);
        customer.setUser(user);
        customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}