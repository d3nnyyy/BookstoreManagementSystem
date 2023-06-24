package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> selectAllAuthors();

    Optional<Author> selectAuthorById(Integer id);

    int addAuthor(Author author);

    int deleteAuthor(Integer id);

    int editAuthor(Integer id, Author author);
}
