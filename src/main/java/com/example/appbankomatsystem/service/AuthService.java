package com.example.appbankomatsystem.service;

import com.example.appbankomatsystem.entity.User;
import com.example.appbankomatsystem.entity.enums.Role;
import com.example.appbankomatsystem.models.LoginDto;
import com.example.appbankomatsystem.models.Result;
import com.example.appbankomatsystem.models.UserDto;
import com.example.appbankomatsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    public Result registerToSystem(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new Result("Email already exists", false);
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.valueOf(userDto.getRole()));
        User savedUser = userRepository.save(user);
        return new Result("User registered successfully", true, savedUser);
    }

    public Result loginToSystem(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()));

            User user = (User) authenticate.getPrincipal();
            return new Result("User successfully enter to system", true, user);
        } catch (Exception e) {
            return new Result("User not found", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
