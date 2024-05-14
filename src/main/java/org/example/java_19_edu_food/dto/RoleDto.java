package org.example.java_19_edu_food.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private int id;
    private String name;
}
