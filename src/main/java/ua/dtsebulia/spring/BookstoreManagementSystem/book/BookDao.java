package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> selectAllBooks();

    Optional<Book> selectBookById(Integer id);

    int addBook(Book book);

    int deleteBook(Integer id);

    int editBook(Integer id, Book book);

    List<Book> selectBooksByAuthor(Integer id);
}
