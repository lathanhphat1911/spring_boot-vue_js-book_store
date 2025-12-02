package com.example.bookstore.Repository;

import com.example.bookstore.DTO.Book.BookResponse;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    public List<Chapter> findChaptersByBook(Book book);
    public List<Chapter> findChaptersByBookId(Long bookId);
}
