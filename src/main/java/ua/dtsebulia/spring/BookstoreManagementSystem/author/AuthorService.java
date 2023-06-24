package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import org.springframework.stereotype.Service;
import ua.dtsebulia.spring.BookstoreManagementSystem.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public List<Author> getAllAuthors() {
        return authorDao.selectAllAuthors();
    }

    public Author getAuthorById(Integer id) {
        return authorDao.selectAuthorById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("There is no author with id %s", id)));
    }

    public void addAuthor(Author author) {
        int result = authorDao.addAuthor(author);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void deleteAuthor(Integer id) {
        Optional<Author> authors = authorDao.selectAuthorById(id);
        authors.ifPresentOrElse(
                presentAuthor -> {
                    int result = authorDao.deleteAuthor(id);
                    if (result != 1) {
                        throw new IllegalStateException("Something went wrong");
                    }
                },
                () -> {
                    throw new NotFoundException(String.format("There is no author with id %s", id));
                }
        );
    }

    public void editAuthor(Integer id, Author author) {
        Optional<Author> authors = authorDao.selectAuthorById(id);
        authors.ifPresentOrElse(
                presentAuthor -> {
                    int result = authorDao.editAuthor(id, author);
                    if (result != 1) {
                        throw new IllegalStateException("Something went wrong");
                    }
                },
                () -> {
                    throw new NotFoundException(String.format("There is no author with id %s", id));
                }
        );
    }
}
