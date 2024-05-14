package org.example.java_19_edu_food.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                                    for (GrantedAuthority authority : authentication.getAuthorities()) {
                                        if (authority.getAuthority().equals("Cafe")) {
                                            response.sendRedirect("/profile");
                                            return;
                                        } else if (authority.getAuthority().equals("Customer")) {
                                            response.sendRedirect("/");
                                            return;
                                        }
                                    }
                                }

                        )
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/dishes/add").hasAuthority("Cafe")
                        .requestMatchers("dishes/delete/{foodId}").hasAuthority("Cafe")
                        .requestMatchers("dishes/deleteCart/{cartId}").hasAuthority("Customer")
                        .requestMatchers("/cart/add").hasAuthority("Buyer")
                        .requestMatchers("/profile/updateQty/{cartId}").hasAuthority("Customer")
                        .requestMatchers("/dishes/{cafe}").permitAll()
                        .anyRequest().fullyAuthenticated()
                );
        return http.build();
    }
}
