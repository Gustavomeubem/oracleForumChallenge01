package com.forum.controller;

import com.forum.dto.AuthRequest;
import com.forum.dto.AuthResponse;
import com.forum.model.User;
import com.forum.security.JwtService;
import com.forum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        
        return ResponseEntity.ok(new AuthResponse(
            token,
            user.getUsername(),
            user.getEmail(),
            user.getRole().name()
        ));
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.username() + "@example.com"); // In production, use proper email field
        user.setRole(User.UserRole.USER);
        
        User savedUser = userService.createUser(user);
        String token = jwtService.generateToken(savedUser);
        
        return ResponseEntity.ok(new AuthResponse(
            token,
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getRole().name()
        ));
    }
}
