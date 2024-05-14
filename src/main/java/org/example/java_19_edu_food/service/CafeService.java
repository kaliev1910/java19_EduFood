package org.example.java_19_edu_food.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.CafeDto;
import org.example.java_19_edu_food.dto.RoleDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.models.Cafe;
import org.example.java_19_edu_food.models.User;
import org.example.java_19_edu_food.repository.CafeRepository;
import org.example.java_19_edu_food.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;

    public Page<CafeDto> getAllCafes(int start, int end) {
        Pageable pageable = PageRequest.of(start, end);
        Page<Cafe> cafes = cafeRepository.findAll(pageable);

        Page<CafeDto> cafeDtos = cafes.map(e -> {
            return CafeDto.builder()
                    .id(e.getCafeId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .address(e.getAddress())
                    .phoneNumber(e.getPhoneNumber())
                    .user(UserDto.builder()
                            .id(e.getUser().getId())
                            .name(e.getUser().getName())
                            .email(e.getUser().getEmail())
                            .role(RoleDto.builder()
                                    .id(e.getUser().getRole().getId())
                                    .name(e.getUser().getRole().getName())
                                    .build())
                            .build())
                    .build();
        });

        return cafeDtos;
    }

    public CafeDto getCafeByName(String name) {

        Cafe cafe = cafeRepository.findByNameIgnoreCase(name).orElse(null);
        if (cafe == null) {
            return null;
        }
        CafeDto cafeDto = CafeDto.builder()
                .id(cafe.getCafeId())
                .name(cafe.getName())
                .build();
        return cafeDto;
    }

    public CafeDto getCafeById(int id) {
        Cafe cafe = cafeRepository.findById(id).orElse(null);
        return CafeDto.builder()
                .id(cafe.getCafeId())
                .address(cafe.getAddress())
                .name(cafe.getName())
                .phoneNumber(cafe.getPhoneNumber())
                .description(cafe.getDescription())
                .user(UserDto.builder()
                        .id(cafe.getUser().getId())
                        .name(cafe.getUser().getName())
                        .build())
                .build();
    }

    public CafeDto getCafeByUserId(int userId) {
        Cafe cafe = cafeRepository.findCafeByUserId(userId);
        return CafeDto.builder()
                .id(cafe.getCafeId())
                .address(cafe.getAddress())
                .name(cafe.getName())
                .phoneNumber(cafe.getPhoneNumber())
                .description(cafe.getDescription())
                .user(UserDto.builder()
                        .id(cafe.getUser().getId())
                        .name(cafe.getUser().getName())
                        .build())
                .build();
    }

    public void save(CafeDto cafeDto) {
        User user = userRepository.findById(cafeDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        cafeRepository.save(Cafe.builder()
                .address(cafeDto.getAddress())
                .phoneNumber(cafeDto.getPhoneNumber())
                .description(cafeDto.getDescription())
                .name(cafeDto.getName())
                .user(user)
                .build());
    }


}
