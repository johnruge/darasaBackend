package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.*;
import com.darasa.darasa_ai.model.User;
import com.darasa.darasa_ai.security.JwtUtils;
import com.darasa.darasa_ai.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${app.signup.code}")
    private String secretSignupCode;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // STEP 1: Validate signup code first ( anti-spam protection!)
            if (!signupRequest.getSignupCode().equals(secretSignupCode)) {
                response.put("timestamp", LocalDateTime.now());
                response.put("status", HttpStatus.FORBIDDEN.value());
                response.put("error", "Invalid signup code");
                response.put("message", "You need a valid invitation code to sign up");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            // STEP 2: Check if email already exists
            if (userService.existsByEmail(signupRequest.getEmail())) {
                response.put("timestamp", LocalDateTime.now());
                response.put("status", HttpStatus.BAD_REQUEST.value());
                response.put("error", "Email already in use");
                response.put("message", "An account with this email already exists");
                return ResponseEntity.badRequest().body(response);
            }

            // STEP 3: Create the user (password gets hashed in UserService)
            User user = userService.createUser(signupRequest);

            // STEP 4: Generate JWT token for immediate login
            String jwt = jwtUtils.generateTokenFromUsername(user.getEmail());

            // STEP 5: Return success response with token
            AuthResponse authResponse = new AuthResponse(
                jwt,
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getId()
            );

            response.put("message", "User registered successfully!");
            response.put("user", authResponse);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "Registration failed");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // STEP 1: Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            // STEP 2: Generate JWT token
            String jwt = jwtUtils.generateJwtToken(authentication);

            // STEP 3: Update last login time
            userService.updateLastLogin(loginRequest.getEmail());

            // STEP 4: Get user details
            User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

            // STEP 5: Return token and user info
            AuthResponse authResponse = new AuthResponse(
                jwt,
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getId()
            );

            response.put("message", "Login successful!");
            response.put("user", authResponse);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("error", "Invalid credentials");
            response.put("message", "Email or password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (Exception e) {
            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", "Login failed");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserByEmail(userDetails.getUsername())
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful! Please remove the token from client storage.");
        return ResponseEntity.ok(response);
    }
}