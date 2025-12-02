package com.example.bookstore.Controller;

import com.example.bookstore.DTO.Book.BookRequest;
import com.example.bookstore.DTO.Book.BookResponse;
import com.example.bookstore.DTO.Chapter.ChapterResponse;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Chapter;
import com.example.bookstore.Entity.Tag;
import com.example.bookstore.Entity.User;
import com.example.bookstore.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/api/store")
    public ResponseEntity<List<BookResponse>> getStore() {
        List<BookResponse> listBook = storeService.findAllBooks();
        return ResponseEntity.ok(listBook);
    }

    @GetMapping("/api/store/{title}")
    public ResponseEntity<List<BookResponse>> getStoreByTitle(@PathVariable("title") String title) {
        List<BookResponse> listBook = storeService.findBookByTitle(title);
        return ResponseEntity.ok(listBook);
    }

    @GetMapping("/api/store/top/{top}")
    public ResponseEntity<List<BookResponse>> getStoreByTop(@PathVariable("top") int top) {
        List<BookResponse> listBook = storeService.findBookTop(top);
        return ResponseEntity.ok(listBook);
    }

    @GetMapping("/api/store/top/tags/{top}")
    public List<Tag> getTopTag(@PathVariable("top") int top) {
        List<Tag> listTag = storeService.findTagTop(top);
        return listTag;
    }

    @GetMapping("/api/store/top/{min}/{max}")
    public ResponseEntity<List<BookResponse>> getBookByTopMinMax(@PathVariable("min") int min, @PathVariable("max") int max) {
        List<BookResponse> listBook = storeService.findBookTopMinMax(min, max);
        return ResponseEntity.ok(listBook);
    }

    @GetMapping("/api/store/top/tags/image/{name}")
    public String getTopTagImage(@PathVariable("name") String name) {
        String imageUrl = storeService.findTagTopAndImage(1, name);
        return imageUrl;
    }

    @GetMapping("/api/store/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id) {
        BookResponse book = storeService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/api/store/tags/by/{bookId}")
    public List<Tag> getBookByTag(@PathVariable("bookId") Long bookId) {
        Book book = storeService.findBookByIdEntity(bookId);
        List<Tag> listTags = storeService.findTagByBook(book);
        return listTags;
    }

    @GetMapping("/api/store/chapter/by/{bookId}")
    public List<ChapterResponse> getChaptersByBook(@PathVariable("bookId") Long bookId) {
        Book book = storeService.findBookByIdEntity(bookId);
        List<ChapterResponse> listChapters = storeService.findChaptersByBook(book);
        return listChapters;
    }

    @GetMapping("/api/store/book/author/{bookId}")
    public ResponseEntity<List<BookResponse>> getBookByAuthor(@PathVariable("bookId") Long bookId) {
        User author = storeService.findAuthorByBookId(bookId);
        List<BookResponse> book = storeService.findBookByAuthor(author);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/api/store/add/book")
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest book) {
        return new ResponseEntity<>(storeService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/api/store/book/chapter/{id}")
    public ResponseEntity<ChapterResponse> getChapterById(@PathVariable("id") Long id) {
        ChapterResponse chapter = storeService.findChapterById(id);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/api/store/book/chapter/next/{id}")
    public ResponseEntity<ChapterResponse> getNextChapter(@PathVariable("id") Long id){
        ChapterResponse chapter = storeService.findChapterById(id);
        Long bookId = chapter.getBookId();
        List<ChapterResponse> chapterResponseList = storeService.findChaptersByBookId(bookId);
        System.out.println(chapterResponseList.size());
        for (int i = 0; i < chapterResponseList.size(); i++) {
            if(chapterResponseList.get(i).getId().equals(id)){
                if(i + 1 < chapterResponseList.size()){
                    System.out.println("tiep tuc + " + chapterResponseList.get(i + 1));
                    return ResponseEntity.ok(chapterResponseList.get(i + 1));
                }else{
                    System.out.println("dung lai");
                    return ResponseEntity.noContent().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/store/book/chapter/previous/{id}")
    public ResponseEntity<ChapterResponse> getPreviousChapter(@PathVariable("id") Long id){
        ChapterResponse chapter = storeService.findChapterById(id);
        Long bookId = chapter.getBookId();
        List<ChapterResponse> chapterResponseList = storeService.findChaptersByBookId(bookId);
        System.out.println(chapterResponseList.size());
        for (int i = chapterResponseList.size() - 1 ; i >= 0; i--) {
            if(chapterResponseList.get(i).getId().equals(id)){
                if(i - 1 > 0){
                    System.out.println("tiep tuc + " + chapterResponseList.get(i - 1));
                    return ResponseEntity.ok(chapterResponseList.get(i - 1));
                }else{
                    System.out.println("dung lai");
                    return ResponseEntity.noContent().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
