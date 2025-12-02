package com.example.bookstore.Repository;

import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t FROM Tag t ORDER BY SIZE(t.books) desc")
    List<Tag> findTagsByBooksCount(Pageable pageable);

    @Query("select t from Tag t JOIN t.books b where t.name = :name order by size(t.books) desc")
    List<Tag> findTagsByBooksCountAndGetImage(Pageable pageable, @Param("name") String name);

    List<Tag> findTagsByBooks(Book book);
}
