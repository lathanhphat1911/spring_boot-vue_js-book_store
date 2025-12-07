package com.example.bookstore.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chapters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String title;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @ManyToOne
    private Book book;
}
