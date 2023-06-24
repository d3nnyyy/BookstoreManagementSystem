package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import org.springframework.jdbc.core.RowMapper;
import ua.dtsebulia.spring.BookstoreManagementSystem.author.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setYear(rs.getInt("year"));
        book.setGenre(rs.getString("genre"));
        book.setPages(rs.getInt("pages"));

        Author author = new Author();
        author.setId(rs.getInt("author_id"));
        author.setName(rs.getString("author_name"));
        author.setAge(rs.getInt("author_age"));

        book.setAuthor(author);

        return book;
    }

}
