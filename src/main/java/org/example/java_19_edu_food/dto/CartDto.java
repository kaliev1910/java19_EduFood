package org.example.java_19_edu_food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartDto {
    private int cartId;
    private int userId;
    private int dishId;
    private int quantity;
    private BigDecimal total;
}
