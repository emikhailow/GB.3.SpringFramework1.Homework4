package com.geekbrains.homework4.services;

import com.geekbrains.homework4.configs.SecurityConfig;
import com.geekbrains.homework4.dto.UserDto;
import com.geekbrains.homework4.entities.Role;
import com.geekbrains.homework4.entities.User;
import com.geekbrains.homework4.exceptions.InternalServerErrorException;
import com.geekbrains.homework4.exceptions.ResourceNotFoundException;
import com.geekbrains.homework4.exceptions.UserAlreadyExistsException;
import com.geekbrains.homework4.repository.RolesRepository;
import com.geekbrains.homework4.repository.UserRepository;
import com.geekbrains.homework4.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RolesService rolesService;
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User createUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistsException(String.format("User %s already exists", userDto.getUsername()));
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists", userDto.getEmail()));
        }
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(rolesService.getDefaultRole()));
        return userRepository.save(user);
    }

    public User getUserFromToken(String token){

        final String username;
        try {
            username = jwtTokenUtil.getUsernameFromToken(token);
        } catch (ExpiredJwtException e) {
            throw  new InternalServerErrorException("Couldn't extract user id from token. Please contact administrator");
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with username %s not found", username)));
    }

}