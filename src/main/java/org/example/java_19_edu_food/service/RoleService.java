package org.example.java_19_edu_food.service;

import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
