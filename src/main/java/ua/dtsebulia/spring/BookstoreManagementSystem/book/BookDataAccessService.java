package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.dtsebulia.spring.BookstoreManagementSystem.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDataAccessService implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> selectAllBooks() {

        var sql = """
                SELECT
                b.id, b.title, b.year, b.genre, b.pages,
                a.id as author_id, a.name as author_name, a.age as author_age
                FROM book b
                INNER JOIN author a ON b.author_id = a.id
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public Optional<Book> selectBookById(Integer id) {

        var sql = """
                SELECT
                b.id, b.title, b.year, b.genre, b.pages,
                a.id as author_id, a.name as author_name, a.age as author_age
                FROM book b
                INNER JOIN author a ON b.author_id = a.id
                WHERE b.id = ?
                """;

        return jdbcTemplate.query(sql, new BookRowMapper(), id)
                .stream()
                .findFirst();

    }

    @Override
    public int addBook(Book book) {

        if (validateAuthorForBook(book)) {
            throw new NotFoundException("Author with id " + book.getAuthor().getId() + " not found");
        }

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

    public boolean validateAuthorForBook(Book book) {

        var authorExistsSql = """
                    SELECT COUNT(*)
                    FROM author
                    WHERE id = ?
                """;

        var authorExists = jdbcTemplate.queryForObject(
                authorExistsSql,
                Integer.class,
                book.getAuthor().getId()
        );

        return authorExists == 0;
    }
}
