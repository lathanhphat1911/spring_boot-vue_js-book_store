package com.example.bookstore.DTO.Book;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponse {
    private Long id;

    private String title;

    private double price;

    private String imageUrl;

    private String description;

    private Long authorId;

    private String authorName;

    private Integer chapterCount;
}
