package org.example.java_19_edu_food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishDto {
    private int id;
    private CafeDto cafeDto;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
}
