package org.example.java_19_edu_food.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dishId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    private String name;
    private String description;

    private BigDecimal price;
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<Cart> carts;


}
