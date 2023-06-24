package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import org.springframework.stereotype.Service;
import ua.dtsebulia.spring.BookstoreManagementSystem.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        return bookDao.selectAllBooks();
    }

    public Book getBookById(Integer id) {
        return bookDao.selectBookById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("There is no book with id %s", id)));
    }

    public void addBook(Book book) {
        int result = bookDao.addBook(book);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void deleteBook(Integer id) {
        Optional<Book> books = bookDao.selectBookById(id);
        books.ifPresentOrElse(
                presentBook -> {
                    int result = bookDao.deleteBook(id);
                    if (result != 1) {
                        throw new IllegalStateException("Something went wrong");
                    }
                },
                () -> {
                    throw new NotFoundException(String.format("There is no book with id %s", id));
                }
        );
    }

    public void editBook(Integer id, Book book) {
        Optional<Book> books = bookDao.selectBookById(id);
        books.ifPresentOrElse(
                presentBook -> {
                    int result = bookDao.editBook(id, book);
                    if (result != 1) {
                        throw new IllegalStateException("Something went wrong");
                    }
                },
                () -> {
                    throw new NotFoundException(String.format("There is no book with id %s", id));
                }
        );
    }

    public List<Book> getBooksByAuthor(Integer id) {
        return bookDao.selectBooksByAuthor(id);
    }
}
