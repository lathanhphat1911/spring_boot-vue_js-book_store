package com.example.bookstore.Repository;

import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String seriesAuthor);

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    User findUserByOwnedBooks(Book book);
}
