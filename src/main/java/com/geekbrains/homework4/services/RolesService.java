package com.geekbrains.homework4.services;

import com.geekbrains.homework4.entities.Role;
import com.geekbrains.homework4.exceptions.InternalServerErrorException;
import com.geekbrains.homework4.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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