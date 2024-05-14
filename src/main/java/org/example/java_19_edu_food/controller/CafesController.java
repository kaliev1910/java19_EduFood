package org.example.java_19_edu_food.controller;

import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.service.CafeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CafesController {
    private static final int PAGE_SIZE = 10;
    private final CafeService cafeService;

    @GetMapping()
    public String getAllRestaurants(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "searchTerm", defaultValue = "null") String searchTerm,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
        }
        if (searchTerm.equals("null")) {
            Page<CafeDto> cafes = cafeService.getAllCafes(page, PAGE_SIZE);
            model.addAttribute("cafes", cafes);
        } else {
            CafeDto cafe = cafeService.getCafeByName(searchTerm);
            if (cafe == null) {
                Page<CafeDto> cafes = cafeService.getAllCafes(page, PAGE_SIZE);
                model.addAttribute("cafes", cafes);
            } else {
                model.addAttribute("cafe", cafe);
            }
        }


        return "restaurants";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String findJob(
            @RequestParam(name = "searchTerm") String searchTerm
    ) {
        return "redirect:/?page=0&searchTerm=" + searchTerm;
    }
}
