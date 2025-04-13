package org.example.graduationprojectbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.graduationprojectbackend.config.JwtUtils;
import org.example.graduationprojectbackend.dao.RoleRepository;
import org.example.graduationprojectbackend.dao.UserRepository;
import org.example.graduationprojectbackend.dto.JwtResponse;
import org.example.graduationprojectbackend.dto.LoginRequest;
import org.example.graduationprojectbackend.dto.SignupRequest;
import org.example.graduationprojectbackend.entity.ERole;
import org.example.graduationprojectbackend.entity.Role;
import org.example.graduationprojectbackend.entity.User;
import org.example.graduationprojectbackend.service.RoleService;
import org.example.graduationprojectbackend.service.UserService;
import org.example.graduationprojectbackend.service.VerificationService;
import org.example.graduationprojectbackend.serviceImpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    VerificationService verificationService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println(6666);
        String UserName = loginRequest.getUsername();
        String Password = loginRequest.getPassword();
        System.out.println(UserName);
        System.out.println(Password);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(UserName, Password));
        } catch (AuthenticationException e) {
            log.error("",e);
            throw new RuntimeException(e
            );
        }

        System.out.println("1");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("2");

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        System.out.println("3");



        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/send-verification-code")
    public ResponseEntity<?> sendVerificationCode(@RequestParam String email) {
        if (userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        try {
            verificationService.sendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending verification code: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // 验证邮箱验证码
        if (!verificationService.verifyCode(signUpRequest.getEmail(), signUpRequest.getVerificationCode())) {
            return ResponseEntity.badRequest().body("Error: Invalid or expired verification code!");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER.name())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_USER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
