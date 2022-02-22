package com.geekbrains.spring.web.auth.services;

import com.geekbrains.spring.web.api.exceptions.InternalServerErrorException;
import com.geekbrains.spring.web.auth.enities.Role;
import com.geekbrains.spring.web.auth.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesService{
    private final RolesRepository rolesRepository;

    public Optional<Role> findByName(String name) {
        return rolesRepository.findByName(name);
    }

    public Role getDefaultRole(){
        return findByName("ROLE_USER")
                .orElseThrow(() -> new InternalServerErrorException("No default role found. Contact administrator"));
    }


}