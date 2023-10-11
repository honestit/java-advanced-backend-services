package com.epam.kupisinski.jabs.springfoundation.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Task3Application {

    public static void main(String[] args) {
        SpringApplication.run(Task3Application.class, args);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                .and().build();
    }

    @Bean
    WebMvcConfigurer webMvcConfigurer(RequestCounterInterceptor requestCounterInterceptor) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(requestCounterInterceptor).addPathPatterns("/**");
            }
        };
    }

}
