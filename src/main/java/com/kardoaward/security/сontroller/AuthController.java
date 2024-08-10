package com.kardoaward.security.сontroller;

import com.kardoaward.security.dto.AuthRequest;
import com.kardoaward.security.dto.AuthResponse;
import com.kardoaward.security.jwt.CustomUserDetails;
import com.kardoaward.security.jwt.JwtUtil;
import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = ((CustomUserDetails) userDetails).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        String token = jwtUtil.generateToken(userDetails.getUsername(), role);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Hidden
    @PostMapping("/register")
    public ResponseEntity<UserProfile> register(@Valid @RequestBody NewUserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}