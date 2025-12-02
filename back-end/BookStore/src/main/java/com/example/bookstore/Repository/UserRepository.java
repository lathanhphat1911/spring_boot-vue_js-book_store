package com.example.bookstore.Repository;

import com.example.bookstore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
