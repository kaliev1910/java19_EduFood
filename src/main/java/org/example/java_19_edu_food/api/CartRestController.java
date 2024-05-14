package org.example.java_19_edu_food.api;

import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CartDto;
import org.example.java_19_edu_food.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartRestController {
    private final CartService cartService;

    @PostMapping("/add")
    public HttpStatus addToCart(@RequestBody CartDto cartDto) {
        if (cartService.isExists(cartDto.getUserId(), cartDto.getDishId())) {
            cartService.updateQuantityAndPrice(cartDto);

        } else {
            cartService.save(cartDto, 1);
        }
        return HttpStatus.OK;
    }


}
