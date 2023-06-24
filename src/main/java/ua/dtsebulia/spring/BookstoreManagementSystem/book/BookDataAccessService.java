package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDataAccessService implements BookDao{

    private final JdbcTemplate jdbcTemplate;

    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> selectAllBooks() {
        var sql = """
            SELECT id, title, year, genre, pages, author_id
            FROM book
            LIMIT 100
            """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Optional<Book> selectBookById(Integer id) {
        var sql = """
            SELECT id, title, year, genre, pages, author_id
            FROM book
            WHERE id = ?
            """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id)
                .stream()
                .findFirst();
    }

    @Override
    public int addBook(Book book) {
        var sql = """
            INSERT INTO book(title, year, genre, pages, author_id)
            VALUES (?, ?, ?, ?, ?);
        """;
        return jdbcTemplate.update(
                sql,
                book.getTitle(),
                book.getYear(),
                book.getGenre(),
                book.getPages(),
                book.getAuthor().getId()
        );
    }

    @Override
    public int deleteBook(Integer id) {
        var sql = """
            DELETE FROM book
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int editBook(Integer id, Book book) {
        var sql = """
            UPDATE book
            SET title = ?, year = ?, genre = ?, pages = ?, author_id = ?
            WHERE id = ?
        """;
        return jdbcTemplate.update(
                sql,
                book.getTitle(),
                book.getYear(),
                book.getGenre(),
                book.getPages(),
                book.getAuthor().getId(),
                id
        );
    }
}
