package org.example.java_19_edu_food.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CafeDto {
    private int id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private UserDto user;
}
