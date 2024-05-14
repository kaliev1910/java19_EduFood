package org.example.java_19_edu_food.service;

import lombok.RequiredArgsConstructor;
import org.example.java_19_edu_food.dto.RoleDto;
import org.example.java_19_edu_food.dto.UserDto;
import org.example.java_19_edu_food.models.Role;
import org.example.java_19_edu_food.models.User;
import org.example.java_19_edu_food.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public int save(UserDto userDto) {
        return userRepository.save(User.builder()
                .id(userDto.getId())
                .password(encoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .role(Role.builder()
                        .id(userDto.getRole().getId())
                        .name(userDto.getRole().getName())
                        .build())
                .email(userDto.getEmail())
                .build()).getId();

    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user == null) {
            return null;
        } else {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(RoleDto.builder()
                            .id(user.getRole().getId())
                            .name(user.getRole().getName())
                            .build())
                    .build();
            return userDto;
        }
    }
}
