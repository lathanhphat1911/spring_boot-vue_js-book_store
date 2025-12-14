package com.example.bookstore.Service;

import com.example.bookstore.DTO.Book.BookRequest;
import com.example.bookstore.DTO.Book.BookResponse;
import com.example.bookstore.DTO.Chapter.ChapterResponse;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Chapter;
import com.example.bookstore.Entity.Tag;
import com.example.bookstore.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    List<BookResponse> findAllBooks();
    List<BookResponse> findBookByTitle(String title);
    BookResponse saveBook(BookRequest book);
    BookResponse updateBook(Long id, Book book);
    void deleteBook(Book book);

    List<BookResponse> findBookTop(int top);

    List<Tag> findTagTop(int top);

    List<BookResponse> findBookTopMinMax(int min, int max);

    String findTagTopAndImage(int i, String name);

    BookResponse findBookById(Long id);

    List<Tag> findTagByBook(Book book);

    Book findBookByIdEntity(Long bookId);

    List<ChapterResponse> findChaptersByBook(Book book);

    User findAuthorByBookId(Long bookId);

    List<BookResponse> findBookByAuthor(User author);

    ChapterResponse findChapterById(Long id);

    List<ChapterResponse> findChaptersByBookId(Long bookId);

    Book findBookNByTitle(String seriesName);

    User findUserByName(String seriesAuthor);

    void saveChapter(Chapter chapter);

    void saveAllChapter(List<Chapter> chapterList);

    List<BookResponse> findBookByTagId(Long tagId);

    List<BookResponse> findBookLikeTitle(String name);

    Boolean checkOwnerShip(String usernameOrEmail, Long id);
}
