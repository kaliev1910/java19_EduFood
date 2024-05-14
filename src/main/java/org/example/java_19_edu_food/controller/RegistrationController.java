package org.example.java_19_edu_food.controller;


import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.dto.RoleDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.service.CafeService;
import org.example.java_19_edu_food.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final CafeService cafeService;


    @GetMapping
    public String register() {
        return "registration";
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String register(
            @RequestParam(name = "name") String accountName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "account_type") String accountValue,
            @RequestParam(name = "cafe_name") String restaurantName,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "phone_number") String phone_number

    ) {
        UserDto userDto = UserDto.builder()
                .password(password)
                .name(accountName)
                .email(email)
                .build();

        if (accountValue.equals("0")) {
            userDto.setRole(RoleDto.builder()
                    .id(1)
                    .name("Customer")
                    .build());
            userService.save(userDto);
        } else {
            userDto.setRole(RoleDto.builder()
                    .id(2)
                    .name("Cafe")
                    .build());
            int userId = userService.save(userDto);
            userDto.setId(userId);
            CafeDto cafeDto = CafeDto.builder()
                    .address(address)
                    .phoneNumber(phone_number)
                    .description(description)
                    .name(restaurantName)
                    .user(userDto)
                    .build();
            cafeService.save(cafeDto);

        }


        return "redirect:/login";

    }


}
