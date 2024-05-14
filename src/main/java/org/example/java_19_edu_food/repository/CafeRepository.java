package org.example.java_19_edu_food.repository;

import org.example.java_19_edu_food.models.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {
    Page<Cafe> findAll(Pageable pageable);

    Optional<Cafe> findByNameIgnoreCase(String name);

    Cafe findCafeByUserId(int userId);


}
