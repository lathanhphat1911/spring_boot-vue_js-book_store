package com.example.bookstore.Repository;

import com.example.bookstore.DTO.Book.BookResponse;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findBookByTitleContainingIgnoreCase(String title);

    public Book findByTitleLike(String title);



    @Query("SELECT b FROM Book b ORDER BY SIZE(b.buyers) desc")
    List<Book> findBooksByBuyerCount(Pageable pageable);

    List<Book> findAllByOrderByBuyersDesc();

    List<Book> findAllByAuthor(User author);

    List<Book> findByTags_Id(Long tagId);

    List<Book> findBookByBuyers(User user);

    boolean existsByIdAndBuyers_Username(Long id, String username);
    boolean existsByIdAndBuyers_Email(Long id, String email);
}
