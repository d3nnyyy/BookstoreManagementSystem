package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return bookService.getBookById(id);
    }

    //get all books by author
    @GetMapping("/author/{id}")
    public List<Book> getBooksByAuthor(@PathVariable("id") Integer id) {
        return bookService.getBooksByAuthor(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
    }

    @PutMapping("{id}")
    public void editBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        bookService.editBook(id, book);
    }

}
