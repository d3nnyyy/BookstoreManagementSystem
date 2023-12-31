package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.dtsebulia.spring.BookstoreManagementSystem.book.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDataAccessService implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> selectAllAuthors() {
        var sql = """
                    SELECT a.id, a.name, a.age
                    FROM author a
                    LIMIT 100
                """;
        List<Author> authors = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class));
        authors.forEach(this::fetchBooksForAuthor);
        return authors;
    }

    @Override
    public Optional<Author> selectAuthorById(Integer id) {
        var sql = """
                    SELECT id, name, age
                    FROM author
                    WHERE id = ?
                """;
        Optional<Author> author = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class), id)
                .stream()
                .findFirst();
        author.ifPresent(this::fetchBooksForAuthor);
        return author;
    }

    @Override
    public int addAuthor(Author author) {
        var sql = """
                    INSERT INTO author(name, age)
                    VALUES (?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                author.getName(),
                author.getAge()
        );
    }

    @Override
    public int deleteAuthor(Integer id) {

        var deleteBooksSql = """
                    DELETE FROM book
                    WHERE author_id = ?
                """;
        jdbcTemplate.update(deleteBooksSql, id);

        var deleteAuthorSql = """
                    DELETE FROM author
                    WHERE id = ?
                """;
        return jdbcTemplate.update(deleteAuthorSql, id);
    }

    @Override
    public int editAuthor(Integer id, Author author) {
        var sql = """
                    UPDATE author
                    SET name = ?, age = ?
                    WHERE id = ?
                """;
        return jdbcTemplate.update(
                sql,
                author.getName(),
                author.getAge(),
                id
        );
    }

    private void fetchBooksForAuthor(Author author) {
        var sql = """
                    SELECT b.id, b.title, b.year, b.genre, b.pages
                    FROM book b
                    WHERE b.author_id = ?
                """;
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), author.getId());
        author.setBooks(books);
    }
}
