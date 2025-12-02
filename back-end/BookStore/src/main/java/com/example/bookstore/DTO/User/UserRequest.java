package com.example.bookstore.DTO.User;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;

    private double budget;

    private String email;
}
