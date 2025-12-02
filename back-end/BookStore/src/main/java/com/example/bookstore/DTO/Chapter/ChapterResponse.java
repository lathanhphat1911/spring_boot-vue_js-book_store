package com.example.bookstore.DTO.Chapter;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChapterResponse {
    private Long id;
    private String title;
    private String content;
    private Long bookId;
}
