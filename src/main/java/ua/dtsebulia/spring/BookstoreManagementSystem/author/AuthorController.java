package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("{id}")
    public Author getAuthorById(@PathVariable("id") Integer id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Integer id) {
        authorService.deleteAuthor(id);
    }

    @PutMapping("{id}")
    public void editAuthor(@PathVariable("id") Integer id, @RequestBody Author author) {
        authorService.editAuthor(id, author);
    }

}
