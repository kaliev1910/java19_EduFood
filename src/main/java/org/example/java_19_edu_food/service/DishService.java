package org.example.java_19_edu_food.service;


import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.dto.DishDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.models.Cafe;
import org.example.java_19_edu_food.models.Dish;
import org.example.java_19_edu_food.repository.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public Page<DishDto> getDishesByCafeId(int restaurantId, int start, int end) {
        Pageable pageable = PageRequest.of(start, end);
        Page<Dish> dishes = dishRepository.findDishesByCafeCafeId(restaurantId, pageable);
        System.out.println(dishes.getTotalPages());
        Page<DishDto> foodDtos = dishes.map(e -> {
            return DishDto.builder()
                    .id(e.getDishId())
                    .cafeDto(CafeDto.builder()
                            .id(e.getCafe().getCafeId())
                            .user(UserDto.builder()
                                    .id(e.getCafe().getUser().getId())
                                    .name(e.getCafe().getUser().getName())
                                    .email(e.getCafe().getUser().getEmail())
                                    .build())
                            .phoneNumber(e.getCafe().getPhoneNumber())
                            .description(e.getCafe().getDescription())
                            .address(e.getCafe().getAddress())
                            .build())
                    .image(e.getImage())
                    .name(e.getName())
                    .price(e.getPrice())
                    .description(e.getDescription())
                    .build();
        });
        return foodDtos;
    }

    public void save(DishDto dishDto) {
        dishRepository.save(Dish.builder()
                .cafe(Cafe.builder()
                        .cafeId(dishDto.getCafeDto().getId())
                        .build())
                .name(dishDto.getName())
                .description(dishDto.getDescription())
                .price(dishDto.getPrice())
                .build());
    }

    public void delete(int foodId) {
        dishRepository.deleteById(foodId);
    }

    public boolean isExists(int foodId, int restaurantId) {
        return dishRepository.existsDishByDishIdAndCafeCafeId(foodId, restaurantId);
    }

}
