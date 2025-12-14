package com.example.bookstore.Service.imp;

import com.example.bookstore.DTO.Book.BookRequest;
import com.example.bookstore.DTO.Book.BookResponse;
import com.example.bookstore.DTO.Chapter.ChapterResponse;
import com.example.bookstore.DTO.Config.JwtAuthenticationResource;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Chapter;
import com.example.bookstore.Entity.Tag;
import com.example.bookstore.Entity.User;
import com.example.bookstore.Repository.BookRepository;
import com.example.bookstore.Repository.ChapterRepository;
import com.example.bookstore.Repository.TagRepository;
import com.example.bookstore.Repository.UserRepository;
import com.example.bookstore.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImplementation implements StoreService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookResponse> findAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(book -> {
                    BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
                    bookResponse.setChapterCount(book.getChapterList() != null ? book.getChapterList().size() : 0);
                    return bookResponse;
                })
                .toList();
    }

    @Override
    public List<BookResponse> findBookByTitle(String title) {
        return bookRepository
                .findBookByTitleContainingIgnoreCase(title)
                .stream()
                .map(book -> {
                    BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
                    bookResponse.setChapterCount(book.getChapterList() != null ? book.getChapterList().size() : 0);
                    return bookResponse;
                })
                .toList();
    }

    @Override
    public List<BookResponse> findBookTop(int top) {
        return bookRepository
                .findBooksByBuyerCount(PageRequest.of(0, top))
                .stream()
                .map(
                        book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    public List<Tag> findTagTop(int top) {
        return tagRepository.findTagsByBooksCount(PageRequest.of(0, top)).stream().toList();
    }

    @Override
    public List<BookResponse> findBookTopMinMax(int min, int max) {
        List<Book> sorted = bookRepository.findAllByOrderByBuyersDesc();

        int start = Math.max(0, min - 1);       // chuyển sang index 0-based
        int end = Math.min(sorted.size(), max); // max không -1 vì subList dùng end-exclusive

        return sorted.subList(start, end)
                .stream()
                .map(b -> modelMapper.map(b, BookResponse.class))
                .toList();
    }

    @Override
    public String findTagTopAndImage(int i, String name) {
        List<Tag> tags = tagRepository.findTagsByBooksCountAndGetImage(PageRequest.of(0, i), name);
        if (tags.isEmpty()) return null;

        Tag tag = tags.get(0);

        if (tag.getBooks() == null || tag.getBooks().isEmpty()) {
            return null;
        }

        List<Book> list = new ArrayList<>(tag.getBooks());
        String image = list.get(1).getImageUrl();
        return image;
    }

    @Override
    public BookResponse findBookById(Long id) {
        return bookRepository
                .findById(id)
                .map(book -> modelMapper.map(book, BookResponse.class))
                .orElse(null);
    }

    @Override
    public List<Tag> findTagByBook(Book book) {
        return tagRepository.findTagsByBooks(book).stream().toList();
    }

    @Override
    public Book findBookByIdEntity(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public List<ChapterResponse> findChaptersByBook(Book book) {
        return chapterRepository.findChaptersByBook(book).stream().map(chapter -> modelMapper.map(chapter, ChapterResponse.class)).toList();
    }

    @Override
    public User findAuthorByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return book.getAuthor();
    }

    @Override
    public List<BookResponse> findBookByAuthor(User author) {
        return bookRepository.findAllByAuthor(author)
                .stream()
                .map(
                        book -> modelMapper.map(book, BookResponse.class)
                ).toList();
    }

    @Override
    public ChapterResponse findChapterById(Long id) {
        return chapterRepository.findById(id)
                .map(
                        chapter -> modelMapper.map(chapter, ChapterResponse.class)
                )
                .orElse(null);
    }

    @Override
    public List<ChapterResponse> findChaptersByBookId(Long bookId) {
        return chapterRepository.findChaptersByBookId(bookId)
                .stream().map(
                        chapter -> modelMapper.map(chapter, ChapterResponse.class)
                ).toList();
    }

//    @Override
//    public void deleteByBook(Book book) {
//        bookRepository.delete(book);
//    }
//
    @Override
    public Book findBookNByTitle(String bookName) {
        return bookRepository.findByTitleLike(bookName);
    }

    @Override
    public User findUserByName(String seriesAuthor) {
        User user = userRepository.findByName(seriesAuthor);

        return user;
    }

    @Override
    public void saveChapter(Chapter chapter) {
        chapterRepository.save(chapter);
    }

    @Override
    public void saveAllChapter(List<Chapter> chapterList) {
        chapterRepository.saveAll(chapterList);
    }

    @Override
    public List<BookResponse> findBookByTagId(Long tagId) {
        return bookRepository.findByTags_Id(tagId).stream().map(book -> modelMapper.map(book, BookResponse.class)).toList();
    }

    @Override
    public List<BookResponse> findBookLikeTitle(String name) {
        return bookRepository.findBookByTitleContainingIgnoreCase(name)
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class)).toList();
    }

    @Override
    public Boolean checkOwnerShip(String usernameOrEmail, Long id) {
        return bookRepository.existsByIdAndBuyers_Username(id, usernameOrEmail)
                || bookRepository.existsByIdAndBuyers_Email(id, usernameOrEmail);
    }

//
//    @Override
//    public void saveAll(List<Chapter> newChapters) {
//
//    }

    @Override
    public BookResponse saveBook(BookRequest book) {
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return bookResponse;
    }

    @Override
    public BookResponse updateBook(Long id, Book book) {
        return null;
    }

    @Override
    public void deleteBook(Book book) {

    }


}
