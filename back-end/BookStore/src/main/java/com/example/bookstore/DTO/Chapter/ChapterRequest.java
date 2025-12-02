package com.example.bookstore.DTO.Chapter;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChapterRequest {
    private String title;
    private String content;
}
