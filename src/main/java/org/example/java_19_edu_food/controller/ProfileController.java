package org.example.java_19_edu_food.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.dto.CartDto;
import org.example.java_19_edu_food.dto.DishDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.service.CafeService;
import org.example.java_19_edu_food.service.CartService;
import org.example.java_19_edu_food.service.DishService;
import org.example.java_19_edu_food.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private static final int PAGE_SIZE = 5;
    private final UserService userService;
    private final DishService dishService;
    private final CafeService cafeService;
    private final CartService cartService;

    @GetMapping
    public String getProfile(
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        authorities.forEach(authority -> log.info("Authority: " + authority.getAuthority()));
        UserDto userDto = userService.getUserByEmail(auth.getName());
        if (userDto.getRole().getId() == 1) {
            model.addAttribute("company", userDto);
            CafeDto cafe = cafeService.getCafeByUserId(userDto.getId());
            if (cafe == null) {
                return "no cafe";
            }
            Page<DishDto> dishes = dishService.getDishesByCafeId(cafe.getId(), page, PAGE_SIZE);
            model.addAttribute("dishes", dishes);
            model.addAttribute("cafe", cafe);
        } else {
            Page<CartDto> carts = cartService.getCartsByCustomerId(userDto.getId(), page, PAGE_SIZE);
            model.addAttribute("carts", carts);
            model.addAttribute("customer", userDto);

        }
        return "profile";
    }

    @PostMapping("/updateQty/{cartId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String updateQty(
            @RequestParam(name = "quantity") int quantity,
            @PathVariable int cartId

    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.getUserByEmail(auth.getName());
        if (cartService.existsByUserIdAndCarId(userDto.getId(), cartId)) {
            cartService.updateCartQty(cartId, quantity);
            return "redirect:/profile";
        } else {
            return "prohibited";
        }
    }
}
