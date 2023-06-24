package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class));
    }

    @Override
    public Optional<Author> selectAuthorById(Integer id) {
        var sql = """
            SELECT id, name, age
            FROM author
            WHERE id = ?
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Author.class), id)
                .stream()
                .findFirst();
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
        var sql = """
            DELETE FROM author
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql, id);
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
}
