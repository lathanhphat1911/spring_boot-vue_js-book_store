package com.example.bookstore.DTO.User;

import com.example.bookstore.Entity.Book;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;

    private String name;

    private String username;

    private double budget;

    private String email;

    private String role;

    private int booksWritten;

    private int booksOwned;
}
