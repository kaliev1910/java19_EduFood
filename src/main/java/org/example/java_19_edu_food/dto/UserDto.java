package org.example.java_19_edu_food.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    private String email;
    private RoleDto role;
    private String password;
}
