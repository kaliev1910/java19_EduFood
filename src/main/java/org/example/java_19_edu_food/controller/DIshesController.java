package org.example.java_19_edu_food.controller;


import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.dto.DishDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.service.CafeService;
import org.example.java_19_edu_food.service.CartService;
import org.example.java_19_edu_food.service.DishService;
import org.example.java_19_edu_food.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DIshesController {
    private static final int PAGE_SIZE = 5;
    private final DishService dishService;
    private final CafeService cafeService;
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/{cafeId}")
    public String getAllFoods(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @PathVariable int cafeId,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            UserDto userDto = userService.getUserByEmail(auth.getName());
            if (userDto.getRole().getId() == 2) {
                model.addAttribute("customer", userDto);
            }
            model.addAttribute("username", auth.getName());
        }
        Page<DishDto> dishes = dishService.getDishesByCafeId(cafeId, page, PAGE_SIZE);
        model.addAttribute("dishes", dishes);
        model.addAttribute("cafe", cafeService.getCafeById(cafeId));
        return "dishes";
    }

    @GetMapping("/add")
    public String getAddForm() {
        return "addDish";
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addFood(
            @RequestParam(name = "dish_name") String dish_name,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") BigDecimal price

    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(auth.getName());
        CafeDto cafe = cafeService.getCafeByUserId(userDto.getId());
        DishDto dish = DishDto.builder()
                .cafeDto(cafe)
                .name(dish_name)
                .description(description)
                .price(price)
                .build();
        dishService.save(dish);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{dishId}")
    public String delete(@PathVariable int dishId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(auth.getName());
        CafeDto cafeDto = cafeService.getCafeByUserId(userDto.getId());
        if (dishService.isExists(dishId, cafeDto.getId())) {
            dishService.delete(dishId);
            return "redirect:/profile";
        } else {
            return "prohibited";
        }
    }

    @GetMapping("/deleteCart/{cartId}")
    public String deleteCart(@PathVariable int cartId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(auth.getName());
        if (cartService.existsByUserIdAndCarId(userDto.getId(), cartId)) {
            cartService.delete(cartId);
            return "redirect:/profile";
        } else {
            return "prohibited";
        }
    }

}
