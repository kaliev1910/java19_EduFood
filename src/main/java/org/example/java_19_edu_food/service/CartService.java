package org.example.java_19_edu_food.service;


import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CartDto;
import org.example.java_19_edu_food.models.Cart;
import org.example.java_19_edu_food.repository.CartRepository;
import org.example.java_19_edu_food.repository.DishRepository;
import org.example.java_19_edu_food.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public void save(CartDto cartDto, int quantity) {
        BigDecimal bigDecimalValue = new BigDecimal(quantity);
        cartRepository.save(Cart.builder()
                .user(userRepository.findById(cartDto.getUserId()).orElse(null))
                .dish(dishRepository.findById(cartDto.getDishId()).orElse(null))
                .quantity(quantity)
                .total(dishRepository.findById(cartDto.getDishId()).orElse(null).getPrice().multiply(bigDecimalValue))
                .build());
    }

    public boolean isExists(int buyerId, int foodId) {
        return cartRepository.existsCartByDishDishIdAndUserId(foodId, buyerId);
    }

    public void updateQuantityAndPrice(CartDto cartDto) {
        Cart existedCart = cartRepository.findCartByDishDishIdAndUserId(cartDto.getDishId(), cartDto.getUserId());
        int quantity = existedCart.getQuantity() + 1;
        BigDecimal qty = new BigDecimal(quantity);
        BigDecimal foodPrice = existedCart.getDish().getPrice();
        BigDecimal totalPrice = foodPrice.multiply(qty);
        cartRepository.updatePriceAndQuantity(existedCart.getCartId(), totalPrice, quantity);
    }

    public Page<CartDto> getCartsByCustomerId(int customerId, int start, int end) {
        Pageable pageable = PageRequest.of(start, end);
        Page<Cart> carts = cartRepository.findCartsByUserId(customerId, pageable);
        Page<CartDto> cartDtos = carts.map(e -> {
            return CartDto.builder()
                    .cartId(e.getCartId())
                    .userId(e.getUser().getId())
                    .dishId(e.getDish().getDishId())
                    .quantity(e.getQuantity())
                    .total(e.getTotal())
                    .build();
        });
        return cartDtos;
    }

    public void delete(int cartId) {
        cartRepository.deleteById(cartId);
    }

    public Boolean existsByUserIdAndCarId(int userId, int cartId) {
        return cartRepository.existsCartByCartIdAndUserId(cartId, userId);
    }

    public void updateCartQty(int cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        BigDecimal price = cart.getDish().getPrice();
        cartRepository.updatePriceAndQuantity(cartId, price.multiply(new BigDecimal(quantity)), quantity);
    }
}
