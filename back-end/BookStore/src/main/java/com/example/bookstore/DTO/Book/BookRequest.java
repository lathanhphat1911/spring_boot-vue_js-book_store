package com.example.bookstore.DTO.Book;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    private String title;

    private Long authorId;

    private double price;

    private String imageUrl;

    private String description;
}
