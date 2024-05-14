package org.example.java_19_edu_food.repository;

import org.example.java_19_edu_food.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Page<Dish> findDishesByCafeCafeId(int restaurantId, Pageable pageable);

    Boolean existsDishByDishIdAndCafeCafeId(int dishId, int cafeId);
}
